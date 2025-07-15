package dev.ghonda.project.management.security.rest.controller;

import dev.ghonda.project.management.shared.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/auth")
public class AuthenticationController {

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<?>> generateToken() {
        return ResponseEntity.ok(ApiResponse.of(null));
    }

}
