package com.radek.zaverecnyProjekt.projekt.Service;

import com.radek.zaverecnyProjekt.projekt.DTO.UserDTO;
import com.radek.zaverecnyProjekt.projekt.DTO.UserDetailDTO;
import com.radek.zaverecnyProjekt.projekt.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Object getAllUsers(boolean detail) {
        String sql = "select * from users";
        if (detail) {
            return jdbcTemplate.query(sql, new RowMapper<UserDetailDTO>() {
                @Override
                public UserDetailDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                    int userId = result.getInt("ID");
                    String name = result.getString("Name");
                    String surname = result.getString("Surname");
                    String personID = result.getString("PersonID");
                    String uuidString = result.getString("UUID");
                    UUID uuid = UUID.fromString(uuidString);
                    return new UserDetailDTO(userId, name, surname, personID, uuid);

                }
            });
        } else {
            return jdbcTemplate.query(sql, new RowMapper<UserDTO>() {
                @Override
                public UserDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                    int userId = result.getInt("ID");
                    String name = result.getString("Name");
                    String surname = result.getString("Surname");
                    return new UserDTO(userId, name, surname);

                }
            });

        }
    }


    public Object getId(int id, boolean detail) {
        if (detail) {
            String sql = "select * from users where ID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<UserDetailDTO>() {
                @Override
                public UserDetailDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                    int userId = result.getInt("ID");
                    String name = result.getString("Name");
                    String surname = result.getString("Surname");
                    String personID = result.getString("PersonID");
                    String uuidString = result.getString("UUID");
                    UUID uuid = UUID.fromString(uuidString);
                    return new UserDetailDTO(userId, name, surname, personID, uuid);
                }
            });
        } else {
            String sql = "select * from users where ID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<UserDTO>() {
                @Override
                public UserDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                    int userId = result.getInt("ID");
                    String name = result.getString("Name");
                    String surname = result.getString("Surname");
                    return new UserDTO(userId, name, surname);
                }
            });
        }
    }

    public void addUser(User user) {
        if (user.getUuid() == null) {
            user.setUuid(UUID.randomUUID());
        }

        String sql = "INSERT INTO users ( Name, Surname,PersonID,UUID) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getPersonID(), user.getUuid().toString());

    }
    public void deleteUserId (int id){
        String sql = "delete from users where ID = ?";
        jdbcTemplate.update(sql, id);
    }
}


