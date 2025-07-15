package dev.ghonda.project.management.security.ports.api.services;

@FunctionalInterface
public interface TextEncoderService {

    String encode(String text);

}
