package com.orion.account.serviceaccountmanager.controller;

import com.orion.account.serviceaccountmanager.dto.ErrorResponseDTO;
import com.orion.account.serviceaccountmanager.dto.UserDto;
import com.orion.account.serviceaccountmanager.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsers_ok(){

        List<UserDto> userDtoList = getUserList();

        when(userService.getAllUsers()).thenReturn(Optional.of(userDtoList));

        ResponseEntity responseEntity = userController.getAllUsers();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        List<UserDto> userDtoList2 = (List<UserDto>) responseEntity.getBody();
        Assertions.assertEquals(false,userDtoList2.isEmpty());

    }

    @Test
    public void getAllUsers_null(){

        when(userService.getAllUsers()).thenReturn(Optional.empty());

        ResponseEntity responseEntity = userController.getAllUsers();

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) responseEntity.getBody();
        Assertions.assertNotNull(errorResponseDTO);

    }



    @Test
    public void getAllUserById_ok(){

        UserDto userDto = new UserDto();
        when(userService.getUserbyId(anyInt())).thenReturn(Optional.of(userDto));

        ResponseEntity responseEntity = userController.getUser(1);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        UserDto userDto2 = (UserDto) responseEntity.getBody();

        Assertions.assertNotNull(userDto2);

    }


    @Test
    public void getAllUserById_empty(){

        when(userService.getUserbyId(anyInt())).thenReturn(Optional.empty());

        ResponseEntity responseEntity = userController.getUser(1);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

        ErrorResponseDTO errorResponseDTO = (ErrorResponseDTO) responseEntity.getBody();

        Assertions.assertNotNull(errorResponseDTO);

    }



    private List<UserDto> getUserList(){
        List<UserDto> userList = new ArrayList<UserDto>();
        UserDto userDto = new UserDto();
        userList.add(userDto);

        return userList;
    }
}
