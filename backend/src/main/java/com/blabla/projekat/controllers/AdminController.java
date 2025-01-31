package com.blabla.projekat.controllers;

import com.blabla.projekat.dto.CaseDTO;
import com.blabla.projekat.dto.ItemDTO;
import com.blabla.projekat.dto.SlotDTO;
import com.blabla.projekat.dto.SlotItemDTO;
import com.blabla.projekat.services.cases.CaseService;
import com.blabla.projekat.services.slot.SlotService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final CaseService caseService;
    private final SlotService slotService;

    public AdminController(CaseService caseService, SlotService slotService) {
        this.caseService = caseService;
        this.slotService = slotService;
    }

    @PostMapping("/addCase")
    public ResponseEntity<Object> addCase(@RequestBody CaseDTO caseDTO) throws IOException {
        System.out.println("GASSGDSLKGJSDLGJDSLK");
        Boolean success = caseService.addCase(caseDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to add a case");

    }
    @PostMapping("/addItem")
    public ResponseEntity<Object> addItem(@RequestBody ItemDTO itemDTO) throws IOException {
        System.out.println("gasermaser");
        Boolean success = caseService.addItem(itemDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to add an item");

    }

    @PostMapping("/addSlot")
    public ResponseEntity<Object> addSlot(@RequestBody SlotDTO slotDTO)  {
        Boolean success = slotService.addSlot(slotDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to add a slot");

    }
    @PostMapping("/addSlotItem/{slotId}")
    public ResponseEntity<Object> addSlotItem(@RequestBody SlotItemDTO slotItemDTO, @PathVariable Long slotId) {
        Boolean success = slotService.addSlotItem(slotItemDTO, slotId);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to add slot item");
    }
}
