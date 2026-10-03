package com.rehan.enterprise.rag;
import com.rehan.enterprise.guardrail.GuardrailService;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class RagService {
 private final GuardrailService guardrail;
 public RagService(GuardrailService g){guardrail=g;}
 public RagResponse query(String question){
  guardrail.validateUserPrompt(question);
  // Provider adapter is intentionally isolated here. Connect Spring AI ChatClient +
  // PgVectorStore for live embeddings/retrieval when an API key is configured.
  List<Citation> citations=List.of(
    new Citation("demo-document","Enterprise AI Architecture Guide",1,0.91));
  return new RagResponse(
    "The platform is configured for grounded retrieval. Connect the model provider and ingest documents to return live answers.",
    citations, true);
 }
 public record Citation(String documentId,String title,int page,double score){}
 public record RagResponse(String answer,List<Citation> citations,boolean grounded){}
}
