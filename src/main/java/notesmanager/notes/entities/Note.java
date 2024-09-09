package notesmanager.notes.entities;


import java.sql.Date;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "notes" )

public class Note {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private Boolean done;
    private Date date;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Note updateWith(Note note){
        return new Note(this.id, note.name, note.description,note.done,note.date,note.getUser());
    }

}
