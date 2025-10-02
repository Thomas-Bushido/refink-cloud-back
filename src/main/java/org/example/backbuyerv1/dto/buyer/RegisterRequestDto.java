package org.example.backbuyerv1.dto.buyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private int phoneNumber;
    private int postalCode;
    private String password;
}
