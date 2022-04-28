package com.orion.account.serviceaccountmanager.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDto {

    private int userId;

    private String name;

    private String surname;

    private List<AccountRequestDTO> accountRequestDTOList = new ArrayList<AccountRequestDTO>();

    public UserDto(){}

    public UserDto(int userId, String name, String surname ) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    public UserDto(int userId, String name, String surname, List<AccountRequestDTO> accountRequestDTOList) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.accountRequestDTOList = accountRequestDTOList;
    }
}
