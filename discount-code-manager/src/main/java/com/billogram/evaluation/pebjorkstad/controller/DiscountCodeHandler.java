package com.billogram.evaluation.pebjorkstad.controller;

import com.billogram.evaluation.pebjorkstad.repository.DiscountCode;
import com.billogram.evaluation.pebjorkstad.repository.DiscountCodeRepository;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscountCodeHandler {

    private final DiscountCodeRepository repository = new DiscountCodeRepository();

    public List<String> generateCodes(UUID brandId, int i) {
        List<String> newCodes = Stream.generate(DiscountCodeHandler::generateCode)
                .limit(i)
                .collect(Collectors.toList());
        repository.saveCodes(brandId, newCodes);
        return newCodes;
    }

    private static String generateCode() {
        return RandomStringUtils.random(6, true, false).toUpperCase();
    }

    public DiscountCode assignCode(UUID brandId, UUID userId) {
        return repository.assignCode(brandId, userId);
    }
}
