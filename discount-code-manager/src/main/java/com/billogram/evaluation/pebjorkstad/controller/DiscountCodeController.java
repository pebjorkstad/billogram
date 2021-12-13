package com.billogram.evaluation.pebjorkstad.controller;

import com.billogram.evaluation.pebjorkstad.api.*;
import com.billogram.evaluation.pebjorkstad.repository.DiscountCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@RestController
@RequestMapping("/discount")
@EnableWebMvc
public class DiscountCodeController {

    private final DiscountCodeHandler handler = new DiscountCodeHandler();

    @PostMapping
    public CreateCodesResponse post(@RequestBody CreateCodesRequest request) {
        List<String> codes = handler.generateCodes(request.getBrandId(), request.getNrOfCodes());
        return CreateCodesResponse.builder()
                .codes(codes)
                .build();
    }

    @GetMapping
    public GetDiscountCodeResponse getCode(@RequestBody GetDiscountCodeRequest request) {
        DiscountCode discountCode = handler.assignCode(request.getBrandId(), request.getUserId());
        return GetDiscountCodeResponse.builder()
                .code(discountCode.getDiscountCode())
                .build();
    }
}
