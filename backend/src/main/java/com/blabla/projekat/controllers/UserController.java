package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.*;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.bet.BetService;
import com.blabla.projekat.services.cases.CaseService;
import com.blabla.projekat.services.slot.SlotService;
import com.blabla.projekat.services.user.UserService;
import com.blabla.projekat.services.skin.SkinService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final CaseService caseService;
    private final SkinService skinService;
//    private final BetService betService;
//    private final SlotService slotService;

    @Autowired
    public UserController(UserService userService, CaseService caseService, SkinService skinService) {

        this.userService = userService;
        this.caseService = caseService;
        this.skinService = skinService;

    }

    @GetMapping("/gas")
    public ResponseEntity<?> res()
    {
        return ResponseEntity.ok("jeeej");
    }


    @GetMapping("/unbox/{userId}/{caseId}")
    public ResponseEntity<SkinDTO> unbox(@PathVariable Long userId, @PathVariable Long caseId)
    {
        SkinDTO skin = caseService.unbox(caseId, userId);
        if (skin!=null)
            return ResponseEntity.ok(skin);
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/getSkins/{userId}")
    public ResponseEntity<List<SkinDTO>> getSkins(@PathVariable Long userId)
    {
        List<SkinDTO> skins = skinService.getSkins(userId);
        if (skins!=null)
            return ResponseEntity.ok(skins);
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/sellSkin/{userId}/{skinId}")
    public ResponseEntity<Boolean> sellSkin(@PathVariable Long userId, @PathVariable Long skinId)
    {
        Boolean success = userService.sellSkin(skinId, userId);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/addBalance/{userId}/{balance}")
    public ResponseEntity<Boolean> addBalance(@PathVariable Long userId, @PathVariable Double balance)
    {
        Boolean success = userService.addBalance(userId, balance);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getProfile/{userId}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long userId)
    {
        ProfileDTO profileDTO = userService.getProfile(userId);
        if (profileDTO != null)
            return ResponseEntity.ok(profileDTO);
        return ResponseEntity.badRequest().build();
    }

//    @PostMapping("/coinflip")
//    public ResponseEntity<CoinflipResponse> coinflip(@RequestBody
//    CoinFlipRequest coinFlipRequest)
//    {
//
//        CoinflipResponse response = betService.coinflip(coinFlipRequest);
//        if (response != null)
//            return ResponseEntity.ok(response);
//        return ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/crash")
//    public ResponseEntity<CrashResponse> crash(@RequestBody
//                                           CrashRequest crashRequest)
//    {
//        CrashResponse response = betService.crash(crashRequest);
//        if (response != null)
//            return ResponseEntity.ok(response);
//        return ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/roulette")
//    public ResponseEntity<RouletteResponse> roulette(@RequestBody
//                                               RouletteRequest rouletteRequest)
//    {
//        RouletteResponse response = betService.roulette(rouletteRequest);
//        if (response != null)
//            return ResponseEntity.ok(response);
//        return ResponseEntity.badRequest().build();
//    }

}
