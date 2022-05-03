package com.orion.account.serviceaccountmanager.service;

import com.orion.account.serviceaccountmanager.dto.UserDto;
import com.orion.account.serviceaccountmanager.entity.UserEntity;
import com.orion.account.serviceaccountmanager.repository.UserEntityRepository;
import com.orion.account.serviceaccountmanager.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserEntityRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findAllUsers_ok(){

        List<UserEntity> userEntityList = new ArrayList<UserEntity>();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setName("satoshi");
        userEntity.setSurname("nakamoto");
        userEntityList.add(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(2);
        userEntity2.setName("scharles");
        userEntity2.setSurname("koskinson");
        userEntityList.add(userEntity2);

        Iterable<UserEntity> it = Arrays.asList(userEntity,userEntity2);

        when(userRepository.findAll()).thenReturn(it);

        Optional<List<UserDto>> optionalUserDtoList = userService.getAllUsers();

        Assertions.assertTrue(optionalUserDtoList.isPresent());
        Assertions.assertFalse(optionalUserDtoList.get().isEmpty());
        Assertions.assertEquals(2,optionalUserDtoList.get().size());

    }


    @Test
    public void getUserById_ok(){

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setName("satoshi");
        userEntity.setSurname("nakamoto");


        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userEntity));

        Optional<UserDto> optional = userService.getUserbyId(1);

        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals("satoshi",optional.get().getName());
        Assertions.assertEquals("nakamoto",optional.get().getSurname());

    }
}
