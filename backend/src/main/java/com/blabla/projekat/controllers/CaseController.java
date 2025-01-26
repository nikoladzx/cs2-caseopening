package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Case;
import com.blabla.projekat.entities.Skin;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.cases.CaseService;
import com.blabla.projekat.services.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<SkinDTO> unbox(@PathVariable Long userId, @PathVariable Long caseId)
    {
        SkinDTO skin = caseService.unbox(caseId, userId);
        if (skin!=null)
            return ResponseEntity.ok(skin);
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/getCase/{caseId}")
    public ResponseEntity<CaseDTO> unbox(@PathVariable Long caseId)
    {
        CaseDTO caseDTO = caseService.getCaseById(caseId);
        if (caseDTO!=null)
            return ResponseEntity.ok(caseDTO);
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/getCases")
    public ResponseEntity<List<CaseDTO>> unbox()
    {
        List<CaseDTO> caseList = caseService.getCases();
        if (caseList!=null)
            return ResponseEntity.ok(caseList);
        return ResponseEntity.badRequest().build();

    }
}
