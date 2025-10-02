package org.example.backbuyerv1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backbuyerv1.dto.buyer.BuyerResponseDto;


import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer {
    @Id
    @GeneratedValue
    private UUID id_buyer;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String city;
    private int phoneNumber;
    private int postalCode;
    private String password;
    private int step =1;

    public Buyer(String firstName, String lastName, String emailAddress, String city, int phoneNumber, int postalCode, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.password = password;
    }

    public BuyerResponseDto entityToDto(){
        return BuyerResponseDto.
                builder().
                id(getId_buyer()).
                firstName(getFirstName()).
                lastName(getLastName()).
                emailAddress(getEmailAddress()).
                step(getStep()).
                build();}
}
