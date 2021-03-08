package com.siit.finalproject.userAccounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
public class Roles {

    @Id
    private int id;

    private String role;
}
