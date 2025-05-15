package com.exemplo.encurtadorurl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SwaggerUrlLogger implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("\n🔗 Swagger UI disponível em: http://localhost:8083/swagger-ui/index.html\n");
    }
}
