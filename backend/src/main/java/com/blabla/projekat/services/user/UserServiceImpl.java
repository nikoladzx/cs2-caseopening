package com.blabla.projekat.services.user;

import com.blabla.projekat.dto.ProfileDTO;
import com.blabla.projekat.dto.UserDTO;
import com.blabla.projekat.entities.Skin;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.ItemRepository;
import com.blabla.projekat.repositories.SkinRepository;
import com.blabla.projekat.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CaseRepository caseRepository;
    private final ItemRepository itemRepository;
    private final SkinRepository skinRepository;

    public UserServiceImpl(UserRepository userRepository, CaseRepository caseRepository, ItemRepository itemRepository, SkinRepository skinRepository) {
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
        this.itemRepository = itemRepository;
        this.skinRepository = skinRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepository.findFirstByEmail(email)
                        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        if (user.isPresent())
        {

            userDTO.setUserRole(user.get().getUserRole());
            userDTO.setId(user.get().getId());
            userDTO.setEmail(user.get().getEmail());
            userDTO.setName(user.get().getName());

        }
        return userDTO;
    }

    @Override
    public Boolean sellSkin(Long skinId, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Skin> optionalSkin = skinRepository.findById(skinId);
        if (optionalUser.isPresent() && optionalSkin.isPresent())
        {
            List<Skin> skinList = optionalUser.get().getSkins().stream().filter(x -> x.getId() == skinId).collect(Collectors.toList());
            if (!skinList.isEmpty()) {
                optionalUser.get().setBalance(optionalUser.get().getBalance() + optionalSkin.get().getPrice());
                optionalUser.get().getSkins().remove(optionalSkin.get());
                //skinRepository.deleteById(skinId);
                skinRepository.delete(optionalSkin.get());


                userRepository.save(optionalUser.get());
                return true;
            }

        }
        return false;
    }

    @Override
    public Boolean addBalance(Long userId, Double balance) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
        {
            optionalUser.get().setBalance(optionalUser.get().getBalance() + balance);
            userRepository.save(optionalUser.get());

            return true;
        }
        return false;
    }

    @Override
    public ProfileDTO getProfile(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
        {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setEmail(optionalUser.get().getEmail());
            profileDTO.setName(optionalUser.get().getName());
            profileDTO.setBalance(optionalUser.get().getBalance());
            return profileDTO;
        }
        return null;
    }

}
