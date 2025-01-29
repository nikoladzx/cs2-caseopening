package com.blabla.projekat.services.bet;

import com.blabla.projekat.dto.CoinFlipRequest;
import com.blabla.projekat.dto.CrashRequest;
import org.springframework.stereotype.Service;

public interface BetService {
    String coinflip(CoinFlipRequest coinFlipRequest);
    Double crash(CrashRequest crashRequest);
}
