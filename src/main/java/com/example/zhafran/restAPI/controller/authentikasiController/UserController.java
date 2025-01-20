package com.example.zhafran.restAPI.controller.authentikasiController;


import com.example.zhafran.restAPI.model.request.authentikasiReq.RegisterUserRequest;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.WebResponse;
import com.example.zhafran.restAPI.service.authentikasiService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return WebResponse.<String>builder().data("success").build();
    }


}
