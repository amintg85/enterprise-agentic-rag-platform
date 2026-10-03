package com.rehan.enterprise.audit;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/audit")
public class AuditController {
 @GetMapping @PreAuthorize("hasRole('ADMIN')")
 public List<Map<String,String>> recent(){
  return List.of(Map.of("event","AUDIT_STREAM","status","Connect persistent audit sink for production"));
 }
}
