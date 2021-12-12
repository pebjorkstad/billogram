package com.billogram.evaluation.pebjorkstad.repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.UUID;

public class DiscountCodeRepository {

    private final DynamoDbEnhancedClient dbClient = DynamoDbEnhancedClient.create();
    private final DynamoDbTable<DiscountCode> discountCodeTable =
            dbClient.table("DiscountCode", TableSchema.fromBean(DiscountCode.class));

    public void saveCodes(UUID brandId, List<String> newDiscountCodes) {
        newDiscountCodes.forEach(code ->
                discountCodeTable.putItem(DiscountCode.builder()
                        .id(UUID.randomUUID())
                        .brandId(brandId)
                        .discountCode(code)
                        .build()));
    }
}
