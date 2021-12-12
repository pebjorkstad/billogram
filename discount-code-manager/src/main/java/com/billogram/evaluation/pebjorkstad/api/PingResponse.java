package com.billogram.evaluation.pebjorkstad.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PingResponse {
    final String pong = "Hello, World!";
}
