package com.example.zhafran.restAPI.controller.authentikasiController;

import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.authentikasiReq.LoginUserRequest;
import com.example.zhafran.restAPI.model.request.authentikasiReq.UpdateUserRequest;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.TokenResponse;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.UserResponse;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.WebResponse;
import com.example.zhafran.restAPI.service.authentikasiService.AuthService;
import com.example.zhafran.restAPI.service.authentikasiService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private RestClient.Builder builder;
    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request){
    TokenResponse tokenResponse = authService.login(request);
    return  WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(User user){
        UserResponse userResponse = userService.get(user);
        return  WebResponse.<UserResponse> builder().data(userResponse).build();
    }


    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request){
        UserResponse userResponse = userService.update(user,request);
        return  WebResponse.<UserResponse> builder().data(userResponse).build();
    }

    @DeleteMapping(
            path = "api/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> logout(User user){
        authService.logut(user);
        return WebResponse.<String>builder().data("logout").build();
    }

}
