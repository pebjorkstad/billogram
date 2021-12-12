package com.billogram.evaluation.pebjorkstad.controller;


import com.billogram.evaluation.pebjorkstad.api.GetDiscountCodeResponse;
import com.billogram.evaluation.pebjorkstad.api.CreateCodesRequest;
import com.billogram.evaluation.pebjorkstad.api.CreateCodesResponse;
import com.billogram.evaluation.pebjorkstad.api.PingResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/discount")
@EnableWebMvc
public class DiscountCodeController {

    private final DiscountCodeHandler handler = new DiscountCodeHandler();

    @GetMapping("/ping")
    public PingResponse ping() {
        return PingResponse.builder().build();
    }

    @PostMapping
    public CreateCodesResponse post(@RequestBody CreateCodesRequest request) {
        List<String> codes = handler.generateCodes(request.getBrandId(), request.getNrOfCodes());
        return CreateCodesResponse.builder().codes(codes).build();
    }

    @GetMapping("/user/{userId}")
    public GetDiscountCodeResponse getCode(@PathVariable UUID userId) {
        return GetDiscountCodeResponse.builder().code(userId.toString()).build();
    }
}
