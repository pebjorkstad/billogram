package com.billogram.evaluation.pebjorkstad.controller;


import com.billogram.evaluation.pebjorkstad.api.PingResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
public class DiscountCodeController {

    @GetMapping("/ping")
    public PingResponse ping() {
        return PingResponse.builder().build();
    }
}
