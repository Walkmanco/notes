package notesmanager.notes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import notesmanager.notes.entities.User;
import notesmanager.notes.reporistories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final  UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User creatUser(User user) {
        User userCopy= new User(user.getId()
                            ,user.getName()
                            ,user.getDni()
                            ,user.getNoteList());

        return userRepository.save(userCopy);
    }

    @Override
    public List<User> showUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
       userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);        
    }
    
}
