package notesmanager.notes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import notesmanager.notes.entities.Note;
import notesmanager.notes.exceptions.NotFoundException;
import notesmanager.notes.reporistories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{

    private final  NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(Note note) {
        Note noteCopy = new Note(note.getId()
                            ,note.getName()
                            ,note.getDescription()
                            ,note.getDone()
                            ,note.getDate()
                            ,note.getUser());

        return noteRepository.save(noteCopy);        
    }

    @Override
    public void deleteAll() {
        noteRepository.deleteAll();        
    }

    @Override
    public void deleteNoteById(Long id) {
        if (noteRepository.findById(id) == null)
            throw new NotFoundException("No se ha encontrado nota con este nombre");
        noteRepository.deleteById(id);        
    }

    @Override
    public List<Note> findbyName(String name) {
        if (noteRepository.findByName(name) == null)
            throw new NotFoundException("No se ha encontrado nota con este nombre");            
        return noteRepository.findByName(name);
    }

    @Override
    public List<Note> showNotes() {        
        return noteRepository.findAll();
    }

    @Override
    public Note updateNote(Long id, Note newNote) {        
        Optional<Note> noteOptional = noteRepository.findById(id);
        
        if(noteOptional.isPresent()){
            Note noteInDb = noteOptional.get();

            Note noteUpdated = noteInDb.updateWith(newNote);
            System.out.println(noteUpdated);
            System.out.println(newNote);
            return noteRepository.save(noteUpdated);
        }else{
            throw new NotFoundException("Nota no encontrada");
        }
    }


    
}
