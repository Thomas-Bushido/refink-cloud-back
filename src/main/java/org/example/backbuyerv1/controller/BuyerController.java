package org.example.backbuyerv1.controller;

import org.example.backbuyerv1.dto.buyer.*;
import org.example.backbuyerv1.entity.Buyer;
import org.example.backbuyerv1.exception.NotFoundException;
import org.example.backbuyerv1.exception.UserAlreadyExistException;
import org.example.backbuyerv1.security.JwtGenerator;
import org.example.backbuyerv1.service.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {
                "http://localhost:4200",
                "https://refink-front.cleverapps.io" // <-- ton app Angular hébergée
        },
        methods = {RequestMethod.GET, RequestMethod.POST}
)
@RestController
@RequestMapping("api/refink/buyer")
public class BuyerController {
    private final BuyerService buyerService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator generator;

    public BuyerController(BuyerService buyerService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtGenerator generator) {
        this.buyerService = buyerService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>
    login(@RequestBody LoginRequestDto loginRequestDTO)
            throws NotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmailAddress(),
                            loginRequestDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String email = authentication.getName();
            Buyer buyer = buyerService.findByEmail(email);
            return ResponseEntity.ok(
                    LoginResponseDto.builder()
                            .token(generator.generateToken(authentication))
                            .id(buyer.getId_buyer())
                            .build()
            );
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }




    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDTO) throws UserAlreadyExistException {
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        Buyer buyer = buyerService.buyerRecord(registerRequestDTO);
        return ResponseEntity.ok(RegisterResponseDto.builder().id(buyer.getId_buyer()).firstName(buyer.getFirstName()).lastName(buyer.getLastName()).emailAddress(buyer.getEmailAddress()).build());
    }


    @GetMapping("/home")
    public ResponseEntity<BuyerResponseDto> getCurrentBuyer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        String email = authentication.getName();
        Buyer buyer = buyerService.findByEmail(email);

        return ResponseEntity.ok(new BuyerResponseDto(
                buyer.getId_buyer(),
                buyer.getFirstName(),
                buyer.getLastName(),
                buyer.getEmailAddress(),
                buyer.getStep()
        ));
    }


}