package org.example.backbuyerv1.security;

import org.example.backbuyerv1.entity.Buyer;
import org.example.backbuyerv1.repository.BuyerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private final BuyerRepository userRepository;

    public CustomUserDetailService(BuyerRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Buyer> userOptional = userRepository.findByEmailAddress(email);
        if (userOptional.isPresent()){
            Buyer buyer = userOptional.get();
            Set<GrantedAuthority> authorities = new HashSet<>();

            return new User(buyer.getEmailAddress(), buyer.getPassword(),authorities);
        }
        return null;
    }
}
