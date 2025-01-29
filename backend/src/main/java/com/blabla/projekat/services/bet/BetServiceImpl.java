package com.blabla.projekat.services.bet;

import com.blabla.projekat.dto.*;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class BetServiceImpl implements BetService{

    private final UserRepository userRepository;

    public BetServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CoinflipResponse coinflip(CoinFlipRequest coinFlipRequest) {
        System.out.println(coinFlipRequest.getUserId());
        System.out.println(coinFlipRequest.getBet());
        System.out.println(coinFlipRequest.getHeads());

        Optional<User> optionalUser = userRepository.findById(coinFlipRequest.getUserId());
        CoinflipResponse response = new CoinflipResponse();
        if (optionalUser.isPresent())
        {
            if (optionalUser.get().getBalance()>coinFlipRequest.getBet())
            {
                //optionalUser.get().setBalance(optionalUser.get().getBalance()-bet);
                Random random = new Random();
                boolean randomBoolean = random.nextBoolean();
                if (randomBoolean == coinFlipRequest.getHeads())
                {
                    optionalUser.get().setBalance(optionalUser.get().getBalance()+coinFlipRequest.getBet());
                    response.setWin(true);

                }
                if (randomBoolean != coinFlipRequest.getHeads())
                {
                    optionalUser.get().setBalance(optionalUser.get().getBalance()-coinFlipRequest.getBet());
                    response.setWin(false);

                }
                userRepository.save(optionalUser.get());
                if (randomBoolean)
                    response.setHeads(true);

                else response.setHeads(false);

            }
        }
        return response;
    }

    @Override
    public CrashResponse crash(CrashRequest crashRequest) {
        Optional<User> optionalUser = userRepository.findById(crashRequest.getUserId());
        Random random = new Random();
        Double mult = 10.0;
        CrashResponse response = new CrashResponse();
        if (optionalUser.isPresent())
        {
            if (optionalUser.get().getBalance()>crashRequest.getBet())
            {
                //optionalUser.get().setBalance(optionalUser.get().getBalance()-bet);

                for (int i=0; i<2; i++)
                {
                    Double randomDouble = random.nextDouble();
                    mult*=randomDouble;
                }
                if (mult<1)
                    mult=1.0;
                response.setMultiplier(mult);
                if (crashRequest.getMultiplier()>mult)
                {
                    optionalUser.get().setBalance(optionalUser.get().getBalance()-crashRequest.getBet());
                    response.setWin(true);


                }
                if (crashRequest.getMultiplier()<mult)
                {
                    response.setWin(false);
                    optionalUser.get()
                            .setBalance(optionalUser.get().getBalance()+(crashRequest.getMultiplier()*crashRequest.getBet()-crashRequest.getBet()));

                }
                userRepository.save(optionalUser.get());

                return response;

            }

        }
        return null;
    }

    @Override
    public RouletteResponse roulette(RouletteRequest rouletteRequest) {
        System.out.println(rouletteRequest.getUserId());
        System.out.println(rouletteRequest.getBet());
        System.out.println(rouletteRequest.getBetOption());
        Optional<User> optionalUser = userRepository.findById(rouletteRequest.getUserId());
        Random random = new Random();
        RouletteResponse rouletteResponse = new RouletteResponse();
        rouletteResponse.setWin(false);
        if (optionalUser.isPresent())
        {
            if (optionalUser.get().getBalance()> rouletteRequest.getBet())
            {


            optionalUser.get().setBalance(optionalUser.get().getBalance() - rouletteRequest.getBet() );
            Long drawnNumber = random.nextLong(37);
            if (drawnNumber==0 && rouletteRequest.getBetOption().equals("green"))
            {
                optionalUser.get().setBalance(optionalUser.get().getBalance() + rouletteRequest.getBet()*36);
                rouletteResponse.setWin(true);
            }

            else if (drawnNumber%2 == 0 && rouletteRequest.getBetOption().equals("black") && drawnNumber!=0)
            {
                optionalUser.get().setBalance(optionalUser.get().getBalance() + rouletteRequest.getBet()*2);
                rouletteResponse.setWin(true);
            }

            else if (drawnNumber%2 == 1 && rouletteRequest.getBetOption().equals("red"))
            {
                optionalUser.get().setBalance(optionalUser.get().getBalance() + rouletteRequest.getBet()*2);
                rouletteResponse.setWin(true);

            }
            rouletteResponse.setDrawnNumber(drawnNumber);
            rouletteResponse.setDrawnColor(drawnNumber==0 ? "green" : drawnNumber%2==0 ? "black" : "red");

            userRepository.save(optionalUser.get());
            return rouletteResponse;
        }}
        return null;
    }
}
