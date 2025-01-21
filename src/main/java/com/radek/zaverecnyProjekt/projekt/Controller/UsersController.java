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
    public Object getAllUsers(@RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail) {
        return userService.getAllUsers(detail);
    }


    @GetMapping("users/{id}")
    public Object getUser(@PathVariable("id") int id, @RequestParam(name = "detail", required = false, defaultValue = "false") boolean detail) {
        try {
            if (!userService.userExistsId(id)) {
                return ResponseEntity.status(404).body("Uživatel s ID " + id + " neexistuje.");
            }
           return userService.getId(id, detail);
        }catch (Exception e) {
                return ResponseEntity.status(500).body("chyba při výpisu uživatele " + e);
        }
    }


    @PostMapping("users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            if (userService.isPersonIdInFile(user.getPersonID())) {
                if (userService.userExistsPersonId(user.getPersonID())) {
                    return ResponseEntity.status(409).body("Uživatel s PersonID " + user.getPersonID() + " již existuje, nelze vložit se stejným PersonID");
                }
                userService.addUser(user);
                return ResponseEntity.ok("Uživatel byl úspěšně přidán.");
            } else {
                return ResponseEntity.status(400).body("PersonID " + user.getPersonID() + " není v seznamu PersonId proto není povoleno vytvoření uživatele.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Chyba při přidávání uživatele." + e);
        }
    }


    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            if (!userService.userExistsId(id)) {
                return ResponseEntity.status(404).body("Uživatel s ID " + id + " neexistuje.");
            }
            userService.deleteUserId(id);
            return ResponseEntity.ok("Uživatel byl úspěšně smazán.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Chyba při mazání uživatele " + e);
        }
    }
    // aktualizace pomocí PathVariable
    @PutMapping("users/{id}")
    public ResponseEntity<String> adjustUser(@PathVariable int id, @RequestBody User user) {
        try {
            if (!userService.userExistsId(id)) {
                return ResponseEntity.status(404).body("Uživatel s ID " + id + " neexistuje.");
            }
            userService.adjustUser(id, user.getName(), user.getSurname());
            return ResponseEntity.ok("Uživatel byl úspěšně aktualizován");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("chyba při aktualizaci uživatele " + e);
        }
    }
    //Aktualizace pomocí RequestBody
    @PutMapping("users")
    public ResponseEntity<String> adjustUser(@RequestBody User user) {
        try {
            if (!userService.userExistsId(user.getID())) {
                return ResponseEntity.status(404).body("Uživatel s ID " + user.getID() + " neexistuje.");
            }
            userService.adjustUser(user.getID(), user.getName(), user.getSurname());
            return ResponseEntity.ok("Uživatel byl úspěšně aktualizován");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("chyba při aktualizaci uživatele " + e);
        }
    }
}


