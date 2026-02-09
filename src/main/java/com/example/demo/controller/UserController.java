package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDTO;
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

    @Operation(summary = "summary",description = "description", responses = {
            @ApiResponse(responseCode = "201",
                        description = "Added user success",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                           examples = @ExampleObject(name = "ex name", summary = "ex summary",
                                           value = """
                                                    {
                                                        "status": 201,
                                                        "message": "User added successfully",
                                                        "data":1
                                                    }
                                                   """
                                           ))
            )
    })
    @PostMapping(value = "/")
    public ResponseSuccess addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User được thêm là: " + userRequestDTO.getFirstName());
        return new ResponseSuccess(HttpStatus.CREATED, "User added success", 1);
    }

    @PutMapping("/{userId}")
    public ResponseSuccess updateUser(@PathVariable("userId") int userId,@RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User update có id = " + userId);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "User updated success");
    }

    @PatchMapping("/{userId}")
    public ResponseSuccess changeStatusUser(@PathVariable("userId") @Min(1) int userId, @RequestParam(required = false) boolean status){
        System.out.println("Request change user status, userId là " + userId);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "User status changed success");
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseSuccess deleteUser(@Min(1) @PathVariable("userId") int userId){
        System.out.println("Delete user có id là " + userId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "User deleted success");
    }

    @GetMapping("/{userId}")
    public ResponseSuccess getUser(@PathVariable("userId") int userId){
        System.out.println("Lây ra userId có id là " + userId);
        return new ResponseSuccess(HttpStatus.OK, "Get user có id " + userId, new UserRequestDTO("tao", "java", "sdt0123", "ksgoku@gmail.com"));
    }

    @GetMapping("/list")
    public ResponseSuccess getAllUsers(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        System.out.println("Lây ra danh sách khàng hàng ở trang " + pageNo);
        return new ResponseSuccess(HttpStatus.OK, "Lây danh sách user",
                List.of(new UserRequestDTO("1","1","sdt1","mail1@"),
                new UserRequestDTO("2","2","sdt2", "mail2@")));
    }

}
