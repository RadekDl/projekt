package com.radek.zaverecnyProjekt.projekt.Controller;

import com.radek.zaverecnyProjekt.projekt.Model.User;
import com.radek.zaverecnyProjekt.projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("api/v1")

public class UsersController {

    @Autowired
    UserService userService;


    @GetMapping("users/")
    public Object getAllUsers(@RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail){
        return userService.getAllUsers(detail);
    }


    @GetMapping("users/{id}")
    public Object getUser(@PathVariable("id") int id, @RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail) {
        return userService.getId(id, detail);
    }


    @PostMapping("users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.ok("Uživatel byl úspěšně přidán.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Chyba při přidávání uživatele."+ e);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUserId(id);
            return ResponseEntity.ok("Uživatel byl úspěšně smazán.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Chyba při mazání uživatele."+ e);
        }
    }
}


