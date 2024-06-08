package com.example.cosmeticmanagement.repository;

import com.example.cosmeticmanagement.model.Cosmetic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {
    Page<Cosmetic> findByNameContainingOrBrandContaining(String name, String brand, Pageable pageable);
}
