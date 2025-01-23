package com.blabla.projekat.services.jwt;

import com.blabla.projekat.dto.UserDTO;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.CaseRepository;
import com.blabla.projekat.repositories.ItemRepository;
import com.blabla.projekat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final CaseRepository caseRepository;
    private final ItemRepository itemRepository;

    public UserServiceImpl(UserRepository userRepository, CaseRepository caseRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
        this.itemRepository = itemRepository;
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

}
