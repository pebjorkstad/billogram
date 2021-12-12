package com.billogram.evaluation.pebjorkstad.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateCodesResponse {
    final List<String> codes;
}
