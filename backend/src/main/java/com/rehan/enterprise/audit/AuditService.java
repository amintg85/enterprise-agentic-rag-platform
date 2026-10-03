package com.rehan.enterprise.audit;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
@Service
public class AuditService {
 private static final Logger log=LoggerFactory.getLogger(AuditService.class);
 public void record(String action,String subject,String outcome){
  log.info("AUDIT timestamp={} action={} subject={} outcome={}",Instant.now(),action,subject,outcome);
 }
}
