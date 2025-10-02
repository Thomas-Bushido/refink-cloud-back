package org.example.backbuyerv1.service;

import lombok.Data;
import org.example.backbuyerv1.dto.buyer.BuyerResponseDto;
import org.example.backbuyerv1.dto.buyer.RegisterRequestDto;
import org.example.backbuyerv1.entity.Buyer;
import org.example.backbuyerv1.exception.UserAlreadyExistException;
import org.example.backbuyerv1.repository.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Data
@Service
public class BuyerService {
    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    public Buyer buyerRecord(RegisterRequestDto registerRequestDto) throws UserAlreadyExistException {
        Optional<Buyer> buyerOptional = buyerRepository.findByEmailAddress(registerRequestDto.getEmailAddress());
        if (buyerOptional.isEmpty()) {
            Buyer buyer = new Buyer(registerRequestDto.getFirstName(), registerRequestDto.getLastName(), registerRequestDto.getEmailAddress(), registerRequestDto.getCity(), registerRequestDto.getPhoneNumber(), registerRequestDto.getPostalCode(), registerRequestDto.getPassword());
           return buyerRepository.save(buyer);
        }
        throw new UserAlreadyExistException();
    }


    public Buyer findByEmail(String emailAddress) {
        return buyerRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new RuntimeException("Acquéreur non trouvé avec l'email: " + emailAddress));
    }

}
