package com.blabla.projekat.services.skin;

import com.blabla.projekat.dto.SkinDTO;
import com.blabla.projekat.entities.Skin;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkinServiceImpl implements SkinService{
    private final UserRepository userRepository;

    public SkinServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<SkinDTO> getSkins(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
        {
            List<SkinDTO> skinList = user.get().getSkins().stream().map(Skin::skinDTO).collect(Collectors.toList());
            return skinList;
        }
        return null;
    }
}
