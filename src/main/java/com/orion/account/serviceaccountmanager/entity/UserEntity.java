package com.orion.account.serviceaccountmanager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class UserEntity {

    @Id
    @Column
    private int userId;

    @Column
    private String name;

    @Column
    private String surname;

    @OneToMany(
            mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
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

    public List<AccountEntity> getAccountEntityList() {
        return accountEntityList;
    }

    public void setAccountEntityList(List<AccountEntity> accountEntityList) {
        this.accountEntityList = accountEntityList;
    }
}
