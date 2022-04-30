package com.orion.account.serviceaccountmanager.controller;

import com.orion.account.serviceaccountmanager.dto.ErrorResponseDTO;
import com.orion.account.serviceaccountmanager.dto.UserDto;
import com.orion.account.serviceaccountmanager.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers(){
        log.info("m=getAllUsers, Getting All Users}");

        Optional<List<UserDto>> optionalUserDtoList = userService.getAllUsers();
        if(optionalUserDtoList.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalUserDtoList.get());
        }else{
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Users List Empty");
            return ResponseEntity.status(HttpStatus.OK).body(errorResponseDTO);
        }
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable("id")Integer userId){

        log.info("m=getUser, customer_id:{}",userId);

        Optional<UserDto> optionalUserDto = userService.getUserbyId(userId);

        if(optionalUserDto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalUserDto.get());
        }else{
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("User not Found");
            return ResponseEntity.status(HttpStatus.OK).body(errorResponseDTO);

        }
    }
}
