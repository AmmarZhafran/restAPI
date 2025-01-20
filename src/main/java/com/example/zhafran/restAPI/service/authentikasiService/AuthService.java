package com.example.zhafran.restAPI.service.authentikasiService;

import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.authentikasiReq.LoginUserRequest;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.TokenResponse;
import com.example.zhafran.restAPI.repository.UserRepository;
import com.example.zhafran.restAPI.security.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Salah"));

    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
        user.setToken(UUID.randomUUID().toString());
        long tokenExpirationTime = calculateTokenExpiration(5);
        user.setTokenExpiredAt(tokenExpirationTime);
        System.out.println("Token Expired At: " + user.getTokenExpiredAt());
        userRepository.save(user);

        return TokenResponse.builder().token(user.getToken()).expiredAt(String.valueOf(user.getTokenExpiredAt())).build();

    }else {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Salah");
    }
    }
    private long calculateTokenExpiration(int minutes) {
        return System.currentTimeMillis() + minutes * 60 * 1000;
    }

    @Transactional
    public void logut(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
