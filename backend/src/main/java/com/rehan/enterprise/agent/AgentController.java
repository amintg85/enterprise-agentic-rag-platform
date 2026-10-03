package com.rehan.enterprise.agent;
import com.rehan.enterprise.guardrail.GuardrailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestController @RequestMapping("/api/agents")
public class AgentController {
 private final GuardrailService guardrail;
 public AgentController(GuardrailService g){guardrail=g;}
 public record AgentRequest(String objective){}
 public record ToolCall(String tool,Map<String,Object> arguments,String authorization){}
 public record AgentResponse(String status,String summary,List<ToolCall> toolCalls){}
 @PostMapping("/run") @PreAuthorize("hasAnyRole('USER','ANALYST','ADMIN')")
 public AgentResponse run(@RequestBody AgentRequest req){
  guardrail.validateUserPrompt(req.objective());
  var call=new ToolCall("systemTime",Map.of(), "READ_ONLY");
  return new AgentResponse("COMPLETED","Authorized read-only tool executed at "+Instant.now(),List.of(call));
 }
}
