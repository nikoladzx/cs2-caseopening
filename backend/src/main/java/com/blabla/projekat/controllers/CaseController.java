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

    private final CaseService caseService;
    @Autowired
    public CaseController(CaseService caseService) {
        this.caseService = caseService;

    }

    @GetMapping("/getCase/{caseId}")
    public ResponseEntity<Object> getCase(@PathVariable Long caseId)
    {
        CaseDTO caseDTO = caseService.getCaseById(caseId);
        if (caseDTO!=null)
            return ResponseEntity.ok(caseDTO);
        return ResponseEntity.badRequest().body("Failed to get a case for id : " + caseId);
    }

    @GetMapping("/getCases")
    public ResponseEntity<Object> getCases()
    {
        List<CaseDTO> caseList = caseService.getCases();
        if (caseList!=null)
            return ResponseEntity.ok(caseList);
        return ResponseEntity.badRequest().body("Failed to get list of cases");


    }

}
