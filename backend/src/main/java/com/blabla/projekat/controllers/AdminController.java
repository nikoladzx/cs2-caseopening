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


    @GetMapping("/gas")
    public ResponseEntity<?> res()
    {
        return ResponseEntity.ok("jeeej");
    }

    @PostMapping("/addCase")
    public ResponseEntity<Boolean> addCase(@RequestBody CaseDTO caseDTO) throws IOException {
        System.out.println("GASSGDSLKGJSDLGJDSLK");
        Boolean success = caseService.addCase(caseDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }
    @PostMapping("/addItem")
    public ResponseEntity<Boolean> addItem(@RequestBody ItemDTO itemDTO) throws IOException {
        System.out.println("gasermaser");
        Boolean success = caseService.addItem(itemDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/addSlot")
    public ResponseEntity<Boolean> addSlot(@RequestBody SlotDTO slotDTO)  {
        Boolean success = slotService.addSlot(slotDTO);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }
    @PostMapping("/addSlotItem/{slotId}")
    public ResponseEntity<Boolean> addSlotItem(@RequestBody SlotItemDTO slotItemDTO, @PathVariable Long slotId) {
        System.out.println("gasermaser");
        System.out.println(slotItemDTO.getMultiplier() + slotItemDTO.getImagepath());
        Boolean success = slotService.addSlotItem(slotItemDTO, slotId);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();

    }
}
