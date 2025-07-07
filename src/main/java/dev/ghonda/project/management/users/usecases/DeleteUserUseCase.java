package dev.ghonda.project.management.users.usecases;

@FunctionalInterface
public interface DeleteUserUseCase {

    void execute(Long userId);

}
