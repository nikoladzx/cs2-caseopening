package com.blabla.projekat.controllers;

import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.services.cases.CaseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final CaseService caseService;

    public AdminController(CaseService caseService) {
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
}
