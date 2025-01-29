package com.blabla.projekat.services.bet;

import com.blabla.projekat.dto.*;
import org.springframework.stereotype.Service;

public interface BetService {
    CoinflipResponse coinflip(CoinFlipRequest coinFlipRequest);
    CrashResponse crash(CrashRequest crashRequest);
    RouletteResponse roulette(RouletteRequest rouletteRequest);
}
