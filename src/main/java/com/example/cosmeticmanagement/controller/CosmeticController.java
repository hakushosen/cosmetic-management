package com.example.cosmeticmanagement.controller;

import com.example.cosmeticmanagement.model.Cosmetic;
import com.example.cosmeticmanagement.service.CosmeticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cosmetics")
public class CosmeticController {
    private static final Logger logger = LoggerFactory.getLogger(CosmeticController.class);

    @Autowired
    private CosmeticService service;

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "name") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
            Model model,
            @RequestParam(value = "keyword", required = false) String keyword) {
        Page<Cosmetic> cosmeticPage;
        if (keyword != null && !keyword.isEmpty()) {
            cosmeticPage = service.searchCosmetics(keyword, page, size, sortField, sortDirection);
            model.addAttribute("keyword", keyword);
        } else {
            cosmeticPage = service.findAllPaginated(page, size, sortField, sortDirection);
        }
        model.addAttribute("cosmeticPage", cosmeticPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        return "list";
    }

    @GetMapping("/add")
    public String addCosmeticForm(Model model) {
        model.addAttribute("cosmetic", new Cosmetic());
        return "add";
    }

    @PostMapping
    public String addCosmetic(@ModelAttribute Cosmetic cosmetic) {
        service.save(cosmetic);
        return "redirect:/cosmetics";
    }

    @GetMapping("/edit/{id}")
    public String editCosmeticForm(@PathVariable Long id, Model model) {
        Optional<Cosmetic> cosmetic = service.findById(id);
        if (cosmetic.isPresent()) {
            model.addAttribute("cosmetic", cosmetic.get());
            return "edit";
        } else {
            return "redirect:/cosmetics";
        }
    }

    @PostMapping("/edit/{id}")
    public String editCosmetic(@PathVariable Long id, @ModelAttribute Cosmetic cosmetic) {
        cosmetic.setId(id);
        service.save(cosmetic);
        return "redirect:/cosmetics";
    }

    @GetMapping("/delete/{id}")
    public String deleteCosmetic(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/cosmetics";
    }


    }

