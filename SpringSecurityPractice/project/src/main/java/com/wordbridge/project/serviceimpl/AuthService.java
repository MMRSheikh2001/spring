package com.wordbridge.project.serviceimpl;

import com.wordbridge.project.dto.requestdto.LoginRequestDTO;
import com.wordbridge.project.dto.responsedto.LoginResponseDTO;
import com.wordbridge.project.entity.User;
import com.wordbridge.project.enums.UserRole;
import com.wordbridge.project.repository.CompanyProfileRepository;
import com.wordbridge.project.repository.UserProfileRepository;
import com.wordbridge.project.repository.UserRepository;
import com.wordbridge.project.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid Email/Password");
        }

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("No User found"));

        Long profileId = null;
        String displayName = "";
        String image = "";
        if (user.getRole() == UserRole.USER) {
            profileId = userProfileRepository.findByUserId(user.getId()).getId();
            displayName = userProfileRepository.findByUserId(user.getId()).getName();
            image = userProfileRepository.findByUserId(user.getId()).getImage();
        }
        if (user.getRole() == UserRole.COMPANY) {
            profileId = companyProfileRepository.findByUserId(user.getId()).getId();
            displayName = companyProfileRepository.findByUserId(user.getId()).getName();
            image = companyProfileRepository.findByUserId(user.getId()).getImage();
        }


        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        LoginResponseDTO response = new LoginResponseDTO();

        response.setToken(token);
        response.setTokenType("Bearer");

        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setProfileId(profileId);
        response.setDisplayName(displayName);
        response.setImage(image);

        return response;

    }

}
