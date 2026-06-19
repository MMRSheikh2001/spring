package com.wordbridge.project.serviceimpl;

import com.wordbridge.project.entity.User;
import com.wordbridge.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("No User Found by "+username));

        String roleAuthority="ROLE_"+user.getRole().name();

        if(!user.isEnabled()){
            throw new DisabledException(
                    "You account is inactive.Contact Admin"
            );

        }




        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(roleAuthority))
        );
    }
}
