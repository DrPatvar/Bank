package com.example.bank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "REST API documentation Bank account",
                version = "1.0.0",
                description = """
                        Приложение для создания Банковских счетов и перевода денег между ними.  
                        """,
                contact = @Contact(name = "Shurkov Ivan", email = "shurkoff@mail.ru")
        )
)
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}
