package notesmanager.notes.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import notesmanager.notes.entities.User;
import notesmanager.notes.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@Validated

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //show
    @GetMapping("/users")
    public ResponseEntity<List<User>> show(){
        List<User> userList = userService.showUsers();

        return ResponseEntity.ok().body(userList);
    }

    //deleteAll
    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteAll(){
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }

    //create
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.creatUser(user);

        URI uriPath = UriComponentsBuilder
            .fromPath("/api/v1/users/{id}")
            .buildAndExpand(createdUser.getId())
            .toUri();
        return ResponseEntity.created(uriPath).body(createdUser);
    }
    

}
