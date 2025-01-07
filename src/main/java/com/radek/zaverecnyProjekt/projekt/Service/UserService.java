package com.radek.zaverecnyProjekt.projekt.Service;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllUser() {
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
                return user;

            }
        });

        return users;

    }

    public List<User> getId(int id) {
        String sql = "select * from users where ID = " + id;
        List<User> user = jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet result, int rowNum) throws SQLException {
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

        public void addUser(User user) {
             if (user.getUuid() == null) {
                user.setUuid(UUID.randomUUID());
            }

            // SQL příkaz pro vložení nového uživatele
            String sql = "INSERT INTO users (Name, Surname, PersonID, UUID) VALUES (?, ?, ?, ?)";

            // Vložení uživatele do databáze
            jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getPersonID(), user.getUuid().toString());
        }
    }


