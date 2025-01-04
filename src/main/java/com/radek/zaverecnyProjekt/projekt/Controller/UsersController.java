package com.radek.zaverecnyProjekt.projekt.Controller;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import com.radek.zaverecnyProjekt.projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("api/v1")

public class UsersController {

    @Autowired
    JdbcTemplate jdbcTemplate;



    @GetMapping("users")
    public List<User> getAllUsers(){
        List<User> users = jdbcTemplate.query("select * from users;", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet result, int rowNum) throws SQLException {
                        User user = new User();
                        user.setID(result.getInt("ID"));
                        user.setName(result.getString("Name"));
                        user.setSurname(result.getString("Surname"));
                        user.setPersonID(result.getString("PersonID"));
                        String uuidString = result.getString("UUID");
                        user.setUuid(UUID.fromString(uuidString));
                        return  user;

                    }
                });

        return users;
    }
    @GetMapping("users{ID}")
    public  User getId(@PathVariable("ID")int id){
        String sql = "select * from users where ID = "+id;
        User user=jdbcTemplate.queryForObject(sql, new RowMapper <User>() {
            public User mapRow(ResultSet result, int rowNum)throws SQLException{
                User user = new User();
                user.setID(result.getInt("ID"));
                user.setName(result.getString("Name"));
                user.setSurname(result.getString("Surname"));
                user.setPersonID(result.getString("PersonID"));
                String uuidString = result.getString("UUID");
                user.setUuid(UUID.fromString(uuidString));
                return user;
            }
        });

        return user;

    }
}
