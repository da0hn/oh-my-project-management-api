package dev.ghonda.project.management;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithStructureVerificationTest {

    @Test
    void shouldHaveValidModularStructure() {
        final ApplicationModules modules = ApplicationModules.of(Application.class);
        modules.verify();
    }

    @Test
    void shouldDocumentModules() {
        final ApplicationModules modules = ApplicationModules.of(Application.class);
        new Documenter(modules).writeDocumentation();
    }

}
