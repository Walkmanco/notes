package notesmanager.notes.controllers;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import notesmanager.notes.dtos.NoteDto;
import notesmanager.notes.entities.Note;
import notesmanager.notes.services.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@Validated

public class NoteController {

    private final NoteService noteService;
    private final ModelMapper mapper;

    public NoteController(NoteService noteService, @Qualifier("DefaultMapper") ModelMapper mapper){
        this.noteService = noteService;
        this.mapper = mapper;
    }

    //show
    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> show(){        
        List<Note> notesList = noteService.showNotes();
        List<NoteDto>notesDtos = null;

        notesDtos = notesList.stream().map(this::noteToNoteDto).toList();
        
        return ResponseEntity.ok().body(notesDtos);
    }

    //delete
    @DeleteMapping("/notes")
    public ResponseEntity<Void> deleteAll(){
        noteService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    //create
    @PostMapping("/notes")
    public ResponseEntity<Note> create(@RequestBody Note note) {
        Note createdNote = noteService.createNote(note);

        //URI uriPath = ServletUriComponentsBuilder.fromCurrentRequest().path(("/id").buildAndExpand(createdNote.getId())).toUri();
        URI uriPath = UriComponentsBuilder
            .fromPath("/api/v1/notes/{id}")
            .buildAndExpand(createdNote.getId())
            .toUri();
        return ResponseEntity.created(uriPath).body(createdNote);
    }
    
    //update
    @PutMapping("/notes/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
            Note note = noteService.updateNote(id, updatedNote);            
        
            return ResponseEntity.ok().body(note);
        }


        public NoteDto noteToNoteDto(Note note){
            return mapper.map(note, NoteDto.class);
        }

        public Note noteDtoToNote(NoteDto noteDto){
            return mapper.map(noteDto, Note.class);
        }

    }
