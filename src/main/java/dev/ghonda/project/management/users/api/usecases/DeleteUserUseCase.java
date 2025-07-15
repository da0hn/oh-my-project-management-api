package dev.ghonda.project.management.users.api.usecases;

@FunctionalInterface
public interface DeleteUserUseCase {

    void execute(Long userId);

}
