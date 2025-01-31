package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.*;
import com.blabla.projekat.entities.SlotItem;
import com.blabla.projekat.services.bet.BetService;
import com.blabla.projekat.services.slot.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bet")
public class BetController {
    private final BetService betService;
    private final SlotService slotService;

    @Autowired
    public BetController(BetService betService, SlotService slotService) {
        this.betService = betService;
        this.slotService = slotService;
    }

    @GetMapping("/getSlots")
    public ResponseEntity<Object> getSlots()
    {
        List<SlotDTO> slotDTOList = slotService.getSlots();
        if (slotDTOList != null)
        {
            return ResponseEntity.ok(slotDTOList);

        }
        return ResponseEntity.badRequest().body("Failed to get list of all slots");
    }

    @GetMapping("/getSlot/{slotId}")
    public ResponseEntity<Object> getSlot(@PathVariable Long slotId)
    {
        List<SlotItemDTO> slotItemDTOList = slotService.getSlotItems(slotId);
        if (slotItemDTOList != null)
        {
            return ResponseEntity.ok(slotItemDTOList);

        }
        return ResponseEntity.badRequest().body("Failed to get a slot for id : " + slotId);
    }

    @PostMapping("/slot")
    public ResponseEntity<Object> slot(@RequestBody SlotRequest slotRequest)
    {
        SlotResponse response = slotService.playSlot(slotRequest);
        if (response != null)
        {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Failed to start a slot");
    }
    @PostMapping("/coinflip")
    public ResponseEntity<Object> coinflip(@RequestBody
                                                     CoinFlipRequest coinFlipRequest)
    {
        System.out.println("gasblabla");
        CoinflipResponse response = betService.coinflip(coinFlipRequest);
        System.out.println(response.getHeads());
        System.out.println(response);
        System.out.println("gk");
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().body("Failed to start a coinflip game");
    }

    @PostMapping("/crash")
    public ResponseEntity<Object> crash(@RequestBody
                                               CrashRequest crashRequest)
    {
        CrashResponse response = betService.crash(crashRequest);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().body("Failed to start a crash game");
    }

    @PostMapping("/roulette")
    public ResponseEntity<Object> roulette(@RequestBody
                                                     RouletteRequest rouletteRequest)
    {
        RouletteResponse response = betService.roulette(rouletteRequest);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().body("Failed to start a roulette game");
    }
}
