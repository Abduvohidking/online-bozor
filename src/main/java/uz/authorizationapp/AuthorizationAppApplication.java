package uz.authorizationapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication()


public class AuthorizationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationAppApplication.class, args);
    }

}
