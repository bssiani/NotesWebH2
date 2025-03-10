package notemanager.service;

import notemanager.model.Note;
import notemanager.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    // The @Autowired annotation is used to inject the NoteRepository bean into the NoteService class
    // This way the CRUD operations (findAll, save, findBy and so on) can be performed on the notes
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note updatedNote) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}