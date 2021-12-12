package com.billogram.evaluation.pebjorkstad.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetDiscountCodeResponse {
    final String code;
}
