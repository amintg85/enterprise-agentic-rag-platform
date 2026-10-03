package com.rehan.enterprise.rag;
import com.rehan.enterprise.audit.AuditService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/rag")
public class RagController {
 private final RagService rag; private final AuditService audit;
 public RagController(RagService r,AuditService a){rag=r;audit=a;}
 public record QueryRequest(String question){}
 @PostMapping("/query")
 public RagService.RagResponse query(@RequestBody QueryRequest request){
  var response=rag.query(request.question());
  audit.record("RAG_QUERY","authenticated-user","SUCCESS");
  return response;
 }
}
