package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDTO;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/")
    public String addUser(@RequestBody UserRequestDTO userRequestDTO){
        return "User added";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") int userId,@RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User update có id = " + userId);
        return "User update";
    }

    @PatchMapping("/{userId}")
    public String changeStatusUser(@PathVariable("userId") int userId, @RequestParam(required = false) boolean status){
        System.out.println("Request change user status, userId là " + userId);
        return "User status changed";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") int userId){
        System.out.println("Delete user có id là " + userId);
        return "User có id là " + userId + " đã được xóa";
    }

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable("userId") int userId){
        System.out.println("Lây ra userId có id là " + userId);
        return new UserRequestDTO("tao", "java", "sdt0123", "ksgoku@gmail.com");
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getAllUsers(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        System.out.println("Lây ra danh sách khàng hàng ở trang " + pageNo);
        return List.of(new UserRequestDTO("1","1","sdt1","mail1@"),
                        new UserRequestDTO("2","2","sdt2", "mail2@"));
    }

}
