package org.example.backbuyerv1.dto.buyer;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int step;



}
