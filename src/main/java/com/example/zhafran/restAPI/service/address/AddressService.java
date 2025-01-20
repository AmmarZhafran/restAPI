package com.example.zhafran.restAPI.service.address;

import com.example.zhafran.restAPI.entity.Address;
import com.example.zhafran.restAPI.entity.Contact;
import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.address.AddressReq;
import com.example.zhafran.restAPI.model.request.address.UpdateAddressRequest;
import com.example.zhafran.restAPI.model.request.contactReq.UpdateContakRequest;
import com.example.zhafran.restAPI.model.response.addressRes.AddressRes;
import com.example.zhafran.restAPI.model.response.contactResponse.ContactResponse;
import com.example.zhafran.restAPI.repository.AddressRepository;
import com.example.zhafran.restAPI.repository.ContactRepository;
import com.example.zhafran.restAPI.service.authentikasiService.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    public AddressRes create(User user, AddressReq  request){
        validationService.validate(request);
        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact is Not Found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContacts(contact);
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setStreet(request.getStreet());
        address.setProvince(request.getProvince());
        address.setPostalCode(request.getPostalCode());
        addressRepository.save(address);

        return toAddressRes(address);
    }

    private AddressRes toAddressRes(Address address) {
        return AddressRes.builder()
                .id(address.getId())
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .build();

    }

    @Transactional
    public AddressRes get(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact is Not Found"));
        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Address is Not Found"));
        addressRepository.save(address);
        return toAddressRes(address);

    }
@Transactional
    public AddressRes update(User user,    UpdateAddressRequest request) {
        validationService.validate(request);
        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact is Not Found"));
        Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Address is Not Found"));
        address.setStreet(request.getStreet());
        address.setProvince(request.getProvince());
        address.setPostalCode(request.getPostalCode());
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());

        addressRepository.save(address);
        return toAddressRes(address);

    }

    @Transactional
    public void delete(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact is Not Found"));
        Address address = addressRepository.findFirstByContactAndId(contact, addressId). orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Address is Not Found"));
        addressRepository.delete(address);
    }

    @Transactional()
    public List<AddressRes> getAll(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact is Not Found"));
    List<Address> addresses =   addressRepository.findAllByContacts(contact);
    return addresses.stream().map(this::toAddressRes).toList();
    }
}
