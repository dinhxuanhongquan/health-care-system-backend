package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentUpdateReq {
    LocalDateTime appointmentDate;     // Ngày giờ hẹn mới (nếu thay đổi)
    Integer duration;                  // Thời lượng khám mới
    String status;                     // Scheduled / Completed / Cancelled
    String type;                       // In-person / Telemedicine

    String reasonForVisit;
    String notes;
    String symptomsDescription;

    Boolean paymentStatus;             // true nếu đã thanh toán
    Boolean paymentMethod;             // true: online, false: tiền mặt

    String cancelledBy;               // Patient / Doctor
    LocalDateTime cancelledAt;
    String cancellationReason;

    LocalDateTime completedAt;
}
