package ru.testitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.testitservice.model.TaskModel;

@SpringBootApplication
public class TestItServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestItServiceApplication.class, args);
    }

}
