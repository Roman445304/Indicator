package com.skills.indicator.Service;

import com.skills.indicator.entity.User;
import com.skills.indicator.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }


}
