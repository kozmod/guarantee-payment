package ru.aora.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.aora.microservice.config.SecurityConfiguration;
import ru.aora.microservice.config.SupportBeanConfiguration;

@SpringBootApplication(scanBasePackages = {
        "ru.aora.microservice.controller",
        "ru.aora.microservice.repositorie",
        "ru.aora.microservice.service"
})
@Import({SecurityConfiguration.class,
        SupportBeanConfiguration.class
})
public class GuaranteePaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(GuaranteePaymentApp.class, args);
    }
}
