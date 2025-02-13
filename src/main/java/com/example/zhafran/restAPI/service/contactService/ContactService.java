package com.example.zhafran.restAPI.service.contactService;

import com.example.zhafran.restAPI.entity.Contact;
import com.example.zhafran.restAPI.entity.User;
import com.example.zhafran.restAPI.model.request.contactReq.CreateContakRequest;
import com.example.zhafran.restAPI.model.request.contactReq.SearchContactReq;
import com.example.zhafran.restAPI.model.request.contactReq.UpdateContakRequest;
import com.example.zhafran.restAPI.model.response.contactResponse.ContactResponse;
import com.example.zhafran.restAPI.repository.ContactRepository;
import com.example.zhafran.restAPI.service.authentikasiService.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ContactResponse create(User user, CreateContakRequest request){
        validationService.validate(request);
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);
        contactRepository.save(contact);
    return toResponse(contact);

    }

    private ContactResponse toResponse(Contact contact){
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    @Transactional(readOnly = true)
    public ContactResponse get(User user, String id){
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact Not Found"));
        contactRepository.save(contact);

        return toResponse(contact);

    }

    @Transactional
    public ContactResponse update(User user, UpdateContakRequest request){
        validationService.validate(request);
        Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact Not Found"));
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contactRepository.save(contact);

        return toResponse(contact);
    }

    @Transactional
    public void delete(User user, String contactId){
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact Not Found"));

        contactRepository.delete(contact);

    }




    @Transactional(readOnly = true)
    public Page<ContactResponse> search(User user, SearchContactReq request){
        Specification<Contact> specification = (root,query,builder)-> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add((Predicate) builder.equal(root.get("user"), user));
            if(Objects.nonNull(request.getName())) {
                predicates.add((Predicate) builder.or(
                        builder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        builder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }
            if(Objects.nonNull(request.getEmail())) {
                predicates.add((Predicate) builder.equal(root.get("email"), request.getEmail()));}
            if(Objects.nonNull(request.getPhone())) {
                predicates.add((Predicate) builder.equal(root.get("phone"), request.getPhone()));
            }
//            return query.where(predicates.toArray(new Predicate[]{}).getRestriction();
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contacts = contactRepository.findAll(specification, pageable);
        List<ContactResponse> contactResponses = contacts.getContent().stream()
                .map(this::toResponse)
                .toList();
        return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
    }

}
