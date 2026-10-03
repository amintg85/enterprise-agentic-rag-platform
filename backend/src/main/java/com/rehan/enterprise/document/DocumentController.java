package com.rehan.enterprise.document;
import com.rehan.enterprise.audit.AuditService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
@RestController @RequestMapping("/api/documents")
public class DocumentController {
 private final DocumentRepository repo; private final AuditService audit;
 public DocumentController(DocumentRepository r,AuditService a){repo=r;audit=a;}
 @GetMapping public List<DocumentRecord> list(){return repo.findAll();}
 @PostMapping(consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
 public ResponseEntity<DocumentRecord> upload(@RequestPart("file") MultipartFile file) throws IOException {
  String type=Optional.ofNullable(file.getContentType()).orElse("");
  if(file.isEmpty() || file.getSize()>20L*1024*1024) return ResponseEntity.badRequest().build();
  if(!Set.of("application/pdf","text/plain").contains(type)) return ResponseEntity.status(415).build();
  DocumentRecord d=repo.save(new DocumentRecord(file.getOriginalFilename(),type,file.getSize(),"QUEUED"));
  audit.record("DOCUMENT_UPLOAD",d.getId().toString(),"ACCEPTED");
  return ResponseEntity.accepted().body(d);
 }
}
