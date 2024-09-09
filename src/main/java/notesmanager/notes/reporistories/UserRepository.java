package notesmanager.notes.reporistories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import notesmanager.notes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    
}
