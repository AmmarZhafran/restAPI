package com.example.zhafran.restAPI.controller.addressController;

import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.address.AddressReq;
import com.example.zhafran.restAPI.model.request.address.UpdateAddressRequest;
import com.example.zhafran.restAPI.model.response.addressRes.AddressRes;
import com.example.zhafran.restAPI.model.response.authentikasiResponse.WebResponse;
import com.example.zhafran.restAPI.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@RestController
public class addressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressRes> create(User user,
                                              @RequestBody AddressReq request,
                                              @PathVariable ("contactId") String contactId
                                              ){
        request.setContactId(contactId);
        AddressRes addressRes = addressService.create(user, request);
        return WebResponse.<AddressRes>builder().data(addressRes).build();
    }


    @GetMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressRes> get(User user, @PathVariable ("contactId") String contactId, @PathVariable ("addressId") String addressId){
        AddressRes addressRes = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressRes>builder().data(addressRes).build();
    }

    @PutMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressRes>update(User user,
//                                         @RequestBody AddressReq request,
                                         @RequestBody UpdateAddressRequest request,
                                         @PathVariable("contactId") String contactId,
                                         @PathVariable ("addressId") String addressId){
        request.setContactId(contactId);
        request.setAddressId(addressId);
        AddressRes addressRes = addressService.update(user,request);
        return WebResponse.<AddressRes>builder().data(addressRes).build();
    }

    @DeleteMapping(
            path = "/api/delete/contacts/{contactId}/addresses/{addressId}"
    )
    public WebResponse<String>delete(User user, @PathVariable("contactId") String contactId,@PathVariable ("addressId") String addressId ){
        addressService.delete(user, contactId, addressId);
        return WebResponse.<String>builder().data("Berhasil di Delete").build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}/addresses/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressRes>> get(User user, @PathVariable ("contactId") String contactId){
       List<AddressRes> addressRes = addressService.getAll(user, contactId);
        return WebResponse.<List<AddressRes>>builder().data(addressRes).build();
    }
}
