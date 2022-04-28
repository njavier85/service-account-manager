package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.dto.AccountRequestDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.dto.UserDto;
import com.orion.account.serviceaccountmanager.entity.UserEntity;
import com.orion.account.serviceaccountmanager.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserEntityRepository userRepository;

    public List<UserDto> getAllUsers(){

        List<UserEntity> userEntityList = new ArrayList<UserEntity>();

        Iterable list = userRepository.findAll();

        userRepository.findAll().forEach(userEntity -> userEntityList.add(userEntity));

        List<UserDto> userDtoList = userEntityList.stream().map(p->new UserDto(p.getUserId(), p.getName(), p.getSurname())).collect(Collectors.toList());

        return userDtoList;
    }

    public Optional<UserDto> getUserbyId(int userId){
        try{
            UserEntity userEntity = userRepository.findById(userId).get();
            if(userEntity!=null){
                UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getName(), userEntity.getSurname(),
                        userEntity.getAccountEntityList().stream().map(p->new AccountRequestDTO(p.getUserEntity().getUserId(),p.getBalance(),
                                p.getTransactionEntityEntityList().stream().map(t->new TransactionRequestDTO(t.getAccountEntity().getAccountId(),t.getAmount(),t.getType(),t.getTransactionId())).collect(Collectors.toList())
                                )).collect(Collectors.toList()));
                return Optional.of(userDto);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return Optional.empty();
    }
}
