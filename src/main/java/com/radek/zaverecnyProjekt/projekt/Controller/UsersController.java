package com.radek.zaverecnyProjekt.projekt.Controller;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import com.radek.zaverecnyProjekt.projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping ("api/v1")

public class UsersController {

    @Autowired
    UserService userService;


    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("users{ID}")
    public  List<User> getId(@PathVariable("ID")int id){
        return userService.getId(id);
    }

}
