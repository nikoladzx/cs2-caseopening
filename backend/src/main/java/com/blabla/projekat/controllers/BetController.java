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
    public ResponseEntity<List<SlotDTO>> getSlots()
    {
        List<SlotDTO> slotDTOList = slotService.getSlots();
        if (slotDTOList != null)
        {
            return ResponseEntity.ok(slotDTOList);

        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getSlot/{slotId}")
    public ResponseEntity<List<SlotItemDTO>> getSlot(@PathVariable Long slotId)
    {
        List<SlotItemDTO> slotItemDTOList = slotService.getSlotItems(slotId);
        if (slotItemDTOList != null)
        {
            return ResponseEntity.ok(slotItemDTOList);

        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/slot")
    public ResponseEntity<SlotResponse> slot(@RequestBody SlotRequest slotRequest)
    {
        SlotResponse response = slotService.playSlot(slotRequest);
        if (response != null)
        {
            return ResponseEntity.ok(response);

        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/coinflip")
    public ResponseEntity<CoinflipResponse> coinflip(@RequestBody
                                                     CoinFlipRequest coinFlipRequest)
    {

        CoinflipResponse response = betService.coinflip(coinFlipRequest);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/crash")
    public ResponseEntity<CrashResponse> crash(@RequestBody
                                               CrashRequest crashRequest)
    {
        CrashResponse response = betService.crash(crashRequest);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/roulette")
    public ResponseEntity<RouletteResponse> roulette(@RequestBody
                                                     RouletteRequest rouletteRequest)
    {
        RouletteResponse response = betService.roulette(rouletteRequest);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }
}
