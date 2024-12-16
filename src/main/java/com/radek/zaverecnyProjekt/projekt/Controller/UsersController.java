package com.radek.zaverecnyProjekt.projekt.Controller;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("api/v1")

public class UsersController {
    @GetMapping("users")
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(
                Connection connect = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/projektTri",
                        "root",
                        "Ford/67")
                ){
            Statement statement = connect.createStatement();
            statement.executeQuery("select * from users");  //zde vložit Querry
            ResultSet result = statement.getResultSet();
            while (result.next()){
                User user = new User();
                //napsat dle konstruktoru
                user.setID(result.getInt("ID"));
                user.setName(result.getString("Name"));
                user.setSurname(result.getString("Surname"));
                user.setPersonID(result.getString("PersonID"));
                user.setUuid(UUID.randomUUID());

                users.add(user);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;

    }
}
