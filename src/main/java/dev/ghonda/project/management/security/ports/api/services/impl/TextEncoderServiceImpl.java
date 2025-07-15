package dev.ghonda.project.management.security.ports.api.services.impl;

import dev.ghonda.project.management.security.ports.api.services.TextEncoderService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TextEncoderServiceImpl implements TextEncoderService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(final String text) {
        return this.passwordEncoder.encode(text);
    }

}
