package com.example.zhafran.restAPI.controller.contactController;

import com.example.zhafran.restAPI.WebConfiguration;
import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.contactReq.CreateContakRequest;
import com.example.zhafran.restAPI.model.request.contactReq.PagingResponsse;
import com.example.zhafran.restAPI.model.request.contactReq.SearchContactReq;
import com.example.zhafran.restAPI.model.request.contactReq.UpdateContakRequest;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.WebResponse;
import com.example.zhafran.restAPI.model.response.contactResponse.ContactResponse;
import com.example.zhafran.restAPI.service.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(
            path = "/api/contacts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse>create(User user, @RequestBody CreateContakRequest request){
        ContactResponse contactResponse = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> get(User user,  @PathVariable("contactId") String contactId){
        ContactResponse contactResponse = contactService.get(user, contactId);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PutMapping(
            path = "/api/contacts/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse>update(User user, @RequestBody UpdateContakRequest request, @PathVariable("contactId") String contactId){
        request.setId(contactId);
        ContactResponse contactResponse = contactService.update(user, request);
        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String>delete(User user,  @PathVariable("contactId") String contactId){
        contactService.delete(user, contactId);
        return WebResponse.<String>builder().data("Berhasil Delete Data").build();
    }
//
    @GetMapping(
            path = "/api/contacts/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ContactResponse>> search(User user,
                                                     @RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "email", required = false) String email,
                                                     @RequestParam(value = "phone", required = false)String phone,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0")Integer page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10")Integer size

    ){

        SearchContactReq request = SearchContactReq.builder().name(name).email(email).phone(phone).page(page).size(size).build();
        Page<ContactResponse> contactResponse = contactService.search(user, request);
        return WebResponse.<List<ContactResponse>>builder()
                .data(contactResponse.getContent())
                .paging(PagingResponsse.builder()
                        .currentPage(contactResponse.getNumber())
                        .size(contactResponse.getSize())
                        .totalPages(contactResponse.getTotalPages())
                        .build())
                .build();

    }
}
