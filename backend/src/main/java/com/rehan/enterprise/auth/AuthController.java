package com.rehan.enterprise.auth;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 public record LoginRequest(String email,String password){}
 public record LoginResponse(String accessToken,String tokenType,long expiresIn,List<String> roles){}
 @PostMapping("/login")
 public LoginResponse login(@RequestBody LoginRequest request){
  // Portfolio scaffold: replace with UserDetailsService + JwtService in production.
  String demo = Base64.getUrlEncoder().withoutPadding().encodeToString(
    (request.email()+":"+Instant.now()).getBytes());
  return new LoginResponse("demo."+demo+".signature","Bearer",1800,List.of("USER"));
 }
}
