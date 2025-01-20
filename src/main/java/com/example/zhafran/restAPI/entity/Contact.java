package com.example.zhafran.restAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class Contact {
    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;



    @OneToMany(mappedBy = "contacts")
    private List<Address> addresses;
}
