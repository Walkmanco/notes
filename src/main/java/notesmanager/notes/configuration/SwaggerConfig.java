package notesmanager.notes.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**") // Puedes ajustar el patrón de las rutas que quieres incluir
                .packagesToScan("notesmanager.notes.controllers") // Ajusta el paquete según tu estructura
                .build();
    }
}