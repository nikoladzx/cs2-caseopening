package com.blabla.projekat.services.bet;

import com.blabla.projekat.dto.CoinFlipRequest;
import com.blabla.projekat.dto.CrashRequest;
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
    public String coinflip(CoinFlipRequest coinFlipRequest) {
        Optional<User> optionalUser = userRepository.findById(coinFlipRequest.getUserId());
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
                    return "WIN";
                }
                if (randomBoolean != coinFlipRequest.getHeads())
                {
                    optionalUser.get().setBalance(optionalUser.get().getBalance()-coinFlipRequest.getBet());
                    return "LOSE";
                }

            }
        }
        return "ERROR";
    }

    @Override
    public Double crash(CrashRequest crashRequest) {
        Optional<User> optionalUser = userRepository.findById(crashRequest.getUserId());
        Random random = new Random();
        Double mult = 10.0;
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
                if (crashRequest.getMultiplier()<mult)
                {
                    optionalUser.get().setBalance(optionalUser.get().getBalance()-crashRequest.getBet());

                }
                if (crashRequest.getMultiplier()>mult)
                {
                    optionalUser.get()
                            .setBalance(optionalUser.get().getBalance()+(crashRequest.getMultiplier()*crashRequest.getBet()-crashRequest.getBet()));

                }
            return mult;

            }

        }
        return null;
    }
}
