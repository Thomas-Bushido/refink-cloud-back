package org.example.backbuyerv1.repository;

import org.example.backbuyerv1.entity.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, UUID> {
    Optional<Buyer> findByEmailAddress(String emailAddress);
}
