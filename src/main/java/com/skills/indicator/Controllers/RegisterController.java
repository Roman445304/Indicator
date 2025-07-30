package com.skills.indicator.Controllers;

import com.skills.indicator.entity.User;
import com.skills.indicator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegisterForm() {
        return "register";
    }

    @PostMapping
    public String processForm (@RequestParam String username,
                               @RequestParam String password,
                               Model model){
        if (userRepository.getUserByUsername(username).isPresent()) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "register";
        }

        userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build());
        return "redirect:/login";
    }
}
