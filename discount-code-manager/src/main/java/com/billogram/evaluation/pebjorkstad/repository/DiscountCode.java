package com.billogram.evaluation.pebjorkstad.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCode {

    private UUID id;
    private String discountCode;
    private UUID brandId;
    private UUID userId;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }
}
