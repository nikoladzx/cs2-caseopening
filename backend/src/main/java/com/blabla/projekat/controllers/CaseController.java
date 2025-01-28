package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.cases.CaseService;
import com.blabla.projekat.services.user.UserService;
import com.blabla.projekat.services.skin.SkinService;
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
    private final SkinService skinService;

    @Autowired
    public CaseController(CaseRepository caseRepository, UserRepository userRepository, UserService userService, CaseService caseService, SkinService skinService) {
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.caseService = caseService;
        this.skinService = skinService;
    }

    @GetMapping("/gas")
    public ResponseEntity<?> res()
    {
        return ResponseEntity.ok("jeeej");
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
