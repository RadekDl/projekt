package com.radek.zaverecnyProjekt.projekt.Controller;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import com.radek.zaverecnyProjekt.projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("api/v1")

public class UsersController {

    @Autowired
    UserService userService;


//    @GetMapping("users")
//    public List<User> getAllUsers(){
//        return userService.getAllUser();
//    }
@GetMapping("users")
    public List<User> getAllUsers(@RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail){
        return userService.getAllUser();
    }


    @GetMapping("users/{id}")
    public Object getUser(@PathVariable("id") int id, @RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail) {
        return userService.getId(id, detail);
    }


    @PostMapping("users")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

}
