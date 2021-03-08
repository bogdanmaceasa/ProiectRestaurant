package com.siit.finalproject.userAccounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
public class Users {

    @Id
    private int id;

    private String name;

    private String email;

    @Column( name = "user_type")
    private int userType;

}
