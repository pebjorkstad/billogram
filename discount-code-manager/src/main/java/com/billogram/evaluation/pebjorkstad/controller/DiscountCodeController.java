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

    /**
     * Allows a brand to create a given amount of discount codes for its brand
     * @param request {@link CreateCodesRequest}
     * @return HTTP 200 OK If the amount of discount codes were generated successfully.
     *                     The generated discount codes will also be returned in the response body.
     */
    @PostMapping
    public CreateCodesResponse post(@RequestBody CreateCodesRequest request) {
        List<String> codes = handler.generateCodes(request.getBrandId(), request.getNrOfCodes());
        return CreateCodesResponse.builder()
                .codes(codes)
                .build();
    }

    /**
     * Used by a user to request a discount code from a brand. If a discount code exists for the brand,
     * a discount code will be assigned to the user and returned in the body of a Http 200 Ok response.
     * @param request {@link GetDiscountCodeRequest}
     * @return HTTP 200 OK        If there was an unused discount code from the brand and it was
     *                            successfully assigned to the user
     *         HTTP 404 NOT FOUND If the brand did not have any free discount codes left
     */
    @GetMapping
    public GetDiscountCodeResponse getCode(@RequestBody GetDiscountCodeRequest request) {
        DiscountCode discountCode = handler.assignCode(request.getBrandId(), request.getUserId());
        return GetDiscountCodeResponse.builder()
                .code(discountCode.getDiscountCode())
                .build();
    }
}
