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

    @GetMapping("/unbox/{userId}/{caseId}")
    public ResponseEntity<Object> unbox(@PathVariable Long userId, @PathVariable Long caseId)
    {
        SkinDTO skin = caseService.unbox(caseId, userId);
        if (skin!=null)
            return ResponseEntity.ok(skin);
        return ResponseEntity.badRequest().body("Failed to unbox a case for user id: " + userId + " and caseId : " + caseId);
    }

    @GetMapping("/getSkins/{userId}")
    public ResponseEntity<Object> getSkins(@PathVariable Long userId)
    {
        List<SkinDTO> skins = skinService.getSkins(userId);
        if (skins!=null)
            return ResponseEntity.ok(skins);
        return ResponseEntity.badRequest().body("Failed to get skins for user with id : "+ userId);
    }

    @DeleteMapping("/sellSkin/{userId}/{skinId}")
    public ResponseEntity<Object> sellSkin(@PathVariable Long userId, @PathVariable Long skinId)
    {
        Boolean success = userService.sellSkin(skinId, userId);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to sell a skin for user id: " + userId + " with skinId : " + skinId);

    }
    @PostMapping("/addBalance/{userId}/{balance}")
    public ResponseEntity<Object> addBalance(@PathVariable Long userId, @PathVariable Double balance)
    {
        Boolean success = userService.addBalance(userId, balance);
        if (success)
            return ResponseEntity.ok(success);
        return ResponseEntity.badRequest().body("Failed to add balance for user with user id: " + userId + " balance amount was : " + balance);
    }

    @GetMapping("/getProfile/{userId}")
    public ResponseEntity<Object> getProfile(@PathVariable Long userId)
    {
        ProfileDTO profileDTO = userService.getProfile(userId);
        if (profileDTO != null)
            return ResponseEntity.ok(profileDTO);
        return ResponseEntity.badRequest().body("Failed to get profile info for user with userId : " + userId);
    }


}
