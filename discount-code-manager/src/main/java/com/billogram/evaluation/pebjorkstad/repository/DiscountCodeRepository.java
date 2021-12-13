package com.billogram.evaluation.pebjorkstad.repository;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Repository
public class DiscountCodeRepository {

    private final DynamoDbEnhancedClient dbClient = DynamoDbEnhancedClient.create();
    private final DynamoDbTable<DiscountCode> table =
            dbClient.table("DiscountCode", TableSchema.fromBean(DiscountCode.class));

    public void saveCodes(UUID brandId, List<String> newDiscountCodes) {
        newDiscountCodes.forEach(code ->
                table.putItem(DiscountCode.builder()
                        .id(UUID.randomUUID())
                        .brandId(brandId)
                        .discountCode(code)
                        .build()));
    }

    public DiscountCode assignCode(UUID brandId, UUID userId) {
        DiscountCode unassignedCode =
                table.scan()
                        .items()
                        .stream()
                        .filter(code -> code.getBrandId().equals(brandId))
                        .filter(code -> Objects.isNull(code.getUserId()))
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);
        table.updateItem(unassignedCode.toBuilder()
                .userId(userId)
                .build());

        return unassignedCode;
    }
}
