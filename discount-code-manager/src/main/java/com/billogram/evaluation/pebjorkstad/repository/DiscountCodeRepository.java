package com.billogram.evaluation.pebjorkstad.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Repository
public class DiscountCodeRepository {

    private final DynamoDbEnhancedClient dbClient = DynamoDbEnhancedClient.create();
    private final DynamoDbTable<DiscountCode> table =
            dbClient.table("DiscountCode", TableSchema.fromBean(DiscountCode.class));

    public DiscountCodeRepository() {
        try {
            table.createTable();
            log.info("'DiscountCode' created");
        } catch (ResourceInUseException e) {
            log.info("'DiscountCode' table already exists, skipping creation of table");
        }
    }

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
