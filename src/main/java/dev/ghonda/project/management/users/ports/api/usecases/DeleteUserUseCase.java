package dev.ghonda.project.management.users.ports.api.usecases;

@FunctionalInterface
public interface DeleteUserUseCase {

    void execute(Long userId);

}
