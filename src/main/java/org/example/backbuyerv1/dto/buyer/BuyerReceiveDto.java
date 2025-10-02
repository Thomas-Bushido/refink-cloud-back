package org.example.backbuyerv1.dto.buyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backbuyerv1.entity.Buyer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerReceiveDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private int phoneNumber;
    private int postalCode;
    private String password;

    public Buyer dtoToEntity(){
        return Buyer.builder().
                firstName(getFirstName()).
                lastName(getLastName()).
                emailAddress(getEmailAddress()).
                city(getCity()).
                phoneNumber(getPhoneNumber()).
                postalCode(getPostalCode()).
                password(getPassword()).
                build();
    }
}
