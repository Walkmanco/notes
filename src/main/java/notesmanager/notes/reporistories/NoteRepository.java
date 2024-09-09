package notesmanager.notes.reporistories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import notesmanager.notes.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

    @Query("SELECT e FROM Note e WHERE e.name=?1")
    List<Note> findByName(@Param("name") String name); 

}
