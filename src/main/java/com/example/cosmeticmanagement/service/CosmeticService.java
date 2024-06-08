package com.example.cosmeticmanagement.service;

import com.example.cosmeticmanagement.model.Cosmetic;
import com.example.cosmeticmanagement.repository.CosmeticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CosmeticService {

    @Autowired
    private CosmeticRepository repository;

    public List<Cosmetic> findAll() {
        return repository.findAll();
    }

    public Page<Cosmetic> findAllPaginated(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField);
        if (sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public Page<Cosmetic> searchCosmetics(String keyword, int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField);
        if (sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findByNameContainingOrBrandContaining(keyword, keyword, pageable);
    }

    public Optional<Cosmetic> findById(Long id) {
        return repository.findById(id);
    }

    public Cosmetic save(Cosmetic cosmetic) {
        return repository.save(cosmetic);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
