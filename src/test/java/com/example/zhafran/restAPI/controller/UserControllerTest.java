//package com.example.zhafran.restAPI.controller;
//
//import com.example.zhafran.restAPI.model.request.authentikasiReq.RegisterUserRequest;
//import com.example.zhafran.restAPI.model.response.authentikasiResponse.WebResponse;
//import com.example.zhafran.restAPI.repository.UserRepository;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void testRegisterSuccess() throws Exception {
//        RegisterUserRequest request = new RegisterUserRequest();
//        request.setUsername("zhafran");
//        request.setPassword("123456");
//        request.setName("zhafran");
//
//        mockMvc.perform(
//                post("/api/users").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
//
//        ).andExpectAll(
//                status().isOk()
//                ).andDo(result ->{
//                 WebResponse<String> response =   objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
//
//                 assertEquals("OK", response.getData());
//                }
//
//                );
//    }
//
//}