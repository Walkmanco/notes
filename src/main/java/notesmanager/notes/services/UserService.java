package notesmanager.notes.services;

import java.util.List;

import notesmanager.notes.entities.User;

public interface UserService {
    User creatUser(User user);
    List<User> showUsers();
    void deleteAllUsers();
    void deleteUserById(Long id);
}
