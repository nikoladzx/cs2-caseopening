package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.entities.Skin;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.cases.CaseService;
import com.blabla.projekat.services.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

@RequestMapping("/api/case")
public class CaseController {
    private final CaseRepository caseRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CaseService caseService;

    @Autowired
    public CaseController(CaseRepository caseRepository, UserRepository userRepository, UserService userService, CaseService caseService) {
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.caseService = caseService;
    }

    @GetMapping("/gas")
    public ResponseEntity<?> res()
    {
        return ResponseEntity.ok("jeeej");
    }
    @PostMapping("/addCase")
    public ResponseEntity<Boolean> addCase(@ModelAttribute CaseDTO caseDTO) throws IOException {
        System.out.println("GASSGDSLKGJSDLGJDSLK");
        Boolean success = caseService.addCase(caseDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }
    @PostMapping("/addItem")
    public ResponseEntity<Boolean> addItem(@ModelAttribute ItemDTO itemDTO) throws IOException {
        System.out.println("gasermaser");
        Boolean success = caseService.addItem(itemDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/unbox/{userId}/{caseId}")
    public ResponseEntity<Skin> unbox(@RequestParam Long caseId, @RequestParam Long userId)
    {
        Skin skin = caseService.unbox(caseId, userId);
        if (skin!=null)
            return ResponseEntity.ok(skin);
        return ResponseEntity.badRequest().build();

    }
}
