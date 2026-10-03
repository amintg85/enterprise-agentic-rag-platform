package com.rehan.enterprise.guardrail;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GuardrailService {
 private static final List<String> INJECTION_PATTERNS=List.of(
   "ignore previous instructions","ignore all instructions","reveal system prompt",
   "show your system prompt","developer message");
 public void validateUserPrompt(String prompt){
  if(prompt==null || prompt.isBlank()) throw new IllegalArgumentException("Prompt is required");
  String p=prompt.toLowerCase();
  if(INJECTION_PATTERNS.stream().anyMatch(p::contains))
   throw new IllegalArgumentException("Potential prompt-injection pattern detected");
 }
}
