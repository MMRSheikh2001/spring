package com.wordbridge.project.serviceimpl;


import com.wordbridge.project.dto.mapper.UserMapper;
import com.wordbridge.project.dto.requestdto.UserRequestDTO;
import com.wordbridge.project.dto.responsedto.UserResponseDTO;
import com.wordbridge.project.entity.User;
import com.wordbridge.project.repository.UserRepository;
import com.wordbridge.project.service.UserService;
import com.wordbridge.project.util.EmailService;
import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @Value("${image.upload.dir}")
//    private String uploadDir;


    private final EmailService emailService;


    private final UserMapper userMapper;

    private final PasswordEncoder encoder;
//    @Override
//    public User save(User u, MultipartFile file) {
//
//        if (file != null && !file.isEmpty()) {
//            String fileName = saveUserImage(file, u);
//            u.setImage(fileName);
//
//        }
//        sendMailToUser(u);
//        return userRepository.save(u);
//
//    }


    @Override
    public UserResponseDTO register(UserRequestDTO dto) {


        if (userRepository.existsByEmail(dto.getEmail().trim().toLowerCase())) {
            throw new RuntimeException("Email already exists");

        }

        User user = userMapper.toEntity(dto);

        user.setPassword(encoder.encode(dto.getPassword()));//Encoding Password
        user.setIsVerified(false);

        user.setIsActive(true);

        user.setIsSuspended(false);

        User savedUser = userRepository.save(user);
        sendMailToUser(savedUser);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found by this Id"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setEmail(dto.getEmail().trim().toLowerCase());

        user.setRole(dto.getRole());


        User updatedUser = userRepository.save(user);
        sendMailToUser(updatedUser);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    //Image save method
//    private String saveUserImage(
//            MultipartFile file,
//            User u) {
//
//
//        Path uploadPath = Paths.get(uploadDir + "users");
//        if (!Files.exists(uploadPath)) {
//            try {
//                Files.createDirectory(uploadPath);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        String userName = u.getName();
//        String fileName = userName.trim().replaceAll("\\s+", "_");
//
//        String savedFileName = fileName + "_" + UUID.randomUUID().toString();
//        Path filePath = uploadPath.resolve(savedFileName);
//        try {
//            Files.copy(file.getInputStream(), filePath);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return savedFileName;
//    }

    //Email Sending to User After register  Method


    public void sendMailToUser(User u) {
        String subject = "Welcome to Our Service – Confirm Your Registration";

        String mailText = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; }"
                + "  .header { background-color: #4CAF50; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }"
                + "  .content { padding: 20px; }"
                + "  .footer { font-size: 0.9em; color: #777; margin-top: 20px; text-align: center; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h2>Welcome to Our Platform</h2>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Dear " + u.getEmail() + ",</p>"
                + "      <p>Thank you for registering with us. We are excited to have you on board!</p>"
                + "      <p>Please confirm your email address to activate your account and get started.</p>"
                + "      <p>If you have any questions or need help, feel free to reach out to our support team.</p>"
                + "      <br>"
                + "      <p>Best regards,<br>The Support Team</p>"
                + "      <p>To Activate Your Account, please click the following link:</p>"
                + "      <p><a href=\"" + " " + "\">Activate Account</a></p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      &copy; " + java.time.Year.now() + " YourCompany. All rights reserved."
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendSimpleMail(u.getEmail(), subject, mailText);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
