package notesmanager.notes.services;

import java.util.List;

import notesmanager.notes.entities.Note;

public interface NoteService {
    
    Note createNote(Note note);
    List<Note> showNotes();
    Note updateNote(Long id, Note newNote);
    void deleteNoteById(Long id);

    void deleteAll();
    List<Note> findbyName(String name);

}
