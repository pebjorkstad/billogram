package com.billogram.evaluation.pebjorkstad.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCodesRequest {
    private UUID brandId;
    private int nrOfCodes;
}
