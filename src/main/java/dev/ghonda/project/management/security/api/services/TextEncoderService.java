package dev.ghonda.project.management.security.api.services;

@FunctionalInterface
public interface TextEncoderService {

    String encode(String text);

}
