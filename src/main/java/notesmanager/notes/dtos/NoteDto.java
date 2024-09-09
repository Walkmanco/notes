package notesmanager.notes.dtos;

import java.sql.Date;

import lombok.Data;

@Data

public class NoteDto {
    private Long id;
    
    private String name;
    private String description;
    private Boolean done;
    private Date date;
    private Long userId;
        
}
