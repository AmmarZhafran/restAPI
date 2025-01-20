package com.example.zhafran.restAPI.repository;

import com.example.zhafran.restAPI.entity.Contact;
import com.example.zhafran.restAPI.entity.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {
     Optional<Contact> findFirstByUserAndId(User user, String id);

}
