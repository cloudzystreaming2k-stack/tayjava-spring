package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDTO;
import com.example.demo.dto.response.ResponseData;
import com.example.demo.dto.response.ResponseError;
import com.example.demo.dto.response.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

//    @PostMapping(value = "/")
//    public ResponseSuccess addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
//        System.out.println("User được thêm là: " + userRequestDTO.getFirstName());
//        return new ResponseSuccess(HttpStatus.CREATED, "User added success", 1);
//    }
    @PostMapping(value = "/")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User được thêm là: " + userRequestDTO.getFirstName());
        //return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Can not create user");
        return new ResponseData<>(HttpStatus.CREATED.value(), "User added success", 1);
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable("userId") int userId,@Valid @RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User update có id = " + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated success");
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatusUser(@PathVariable("userId") @Min(1) int userId, @RequestParam(required = false) boolean status){
        System.out.println("Request change user status, userId là " + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status changed success");
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseData<?> deleteUser(@Min(1) @PathVariable("userId") int userId){
        System.out.println("Delete user có id là " + userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted success");
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable("userId") int userId){
        System.out.println("Lây ra userId có id là " + userId);
        return new ResponseData<UserRequestDTO>(HttpStatus.OK.value(), "Get user có id " + userId, new UserRequestDTO("tao", "java", "sdt0123", "ksgoku@gmail.com"));
    }

    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers(@RequestParam(defaultValue = "10") int pageNo, @RequestParam(defaultValue = "20") int pageSize){
        System.out.println("Lây ra danh sách khàng hàng ở trang " + pageNo);
        return new ResponseData<List<UserRequestDTO>>(HttpStatus.OK.value(), "Lây danh sách user",
                List.of(new UserRequestDTO("1","1","sdt1","mail1@"),
                new UserRequestDTO("2","2","sdt2", "mail2@")));
    }

}
