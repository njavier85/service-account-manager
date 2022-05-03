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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<AccountRequestDTO> getAccountRequestDTOList() {
        return accountRequestDTOList;
    }

    public void setAccountRequestDTOList(List<AccountRequestDTO> accountRequestDTOList) {
        this.accountRequestDTOList = accountRequestDTOList;
    }
}
