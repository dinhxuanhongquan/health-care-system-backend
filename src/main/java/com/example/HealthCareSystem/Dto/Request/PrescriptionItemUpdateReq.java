package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionItemUpdateReq {
    Integer quantity;
    Double totalPrice;

    String prescriptionId;
    String productId;
}
