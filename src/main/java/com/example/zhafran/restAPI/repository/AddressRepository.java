package com.example.zhafran.restAPI.repository;

import com.example.zhafran.restAPI.entity.Address;
import com.example.zhafran.restAPI.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    @Query("SELECT a FROM Address a WHERE a.contacts = :contact AND a.id = :id")
    Optional<Address> findFirstByContactAndId(Contact contact, String id);

//    List<Address> findAllByContact(Contact contact);
    List<Address> findAllByContacts(Contact contact);

//@Query("SELECT a FROM Address a WHERE a.contacts = :contact AND a.id = :id")
//Optional<Address> findByContactAndId(@Param("contact") Contact contact, @Param("id") String id);

}
