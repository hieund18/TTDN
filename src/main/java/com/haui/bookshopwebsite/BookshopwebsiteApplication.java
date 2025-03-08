package com.haui.bookshopwebsite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
@OpenAPIDefinition()
public class BookshopwebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshopwebsiteApplication.class, args);
    }


}
