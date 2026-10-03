package com.rehan.enterprise.rag;

import com.rehan.enterprise.audit.AuditService;
import com.rehan.enterprise.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RagController.class)
@Import(SecurityConfig.class)
class RagControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RagService ragService;

    @MockBean
    private AuditService auditService;

    @Test
    void publicDemoQueryReturnsAnswerAndCitation() throws Exception {
        when(ragService.query("incident response")).thenReturn(new RagService.RagResponse(
            "Demo answer", List.of(new RagService.Citation("demo-document", "Demo guide", 1, 0.91)), true));

        mockMvc.perform(post("/api/rag/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"question\":\"incident response\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.answer").value("Demo answer"))
            .andExpect(jsonPath("$.citations[0].documentId").value("demo-document"));

        verify(auditService).record("RAG_QUERY", "authenticated-user", "SUCCESS");
    }
}