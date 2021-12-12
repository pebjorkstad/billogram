package com.billogram.evaluation.pebjorkstad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.billogram.evaluation.pebjorkstad.controller.DiscountCodeController;


@SpringBootApplication
@Import({ DiscountCodeController.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
