package notemanager.controller;

import notemanager.model.Note;
import notemanager.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

// This class is the controller that handles the requests and returns the responses to the /notes URL.

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    // When a HTTP GET request is received to to the /notes URL, @GetMapping will invoke this method
    // The method getAllNotes retrieves all notes from the database and adds them to the model, which passes the data to the view.
    
    @GetMapping("/notes")
    public String getAllNotes(Model model, @RequestParam(required = false) String sort) {
        List<Note> notes;
    
        if ("urgency".equals(sort)) {
            notes = noteService.getNotesSortedByUrgency();
        } else if ("date".equals(sort)) {
            notes = noteService.getNotesSortedByDate();
        } else {
            notes = noteService.getAllNotes();
        }

        model.addAttribute("notes", notes);
        
        return "view-notes";
    }

    // This request will redirect to the form to create a new note

    @GetMapping("/notes/create")
    public String showCreateForm() {
        return "create-note";
    }

    // When a POST request is received to the /notes/create URL, PostMapping will do the same and invoke this method.
    // The method createNote creates a new note with the title and content received from the form, as well with a due date if present,
    // the total amount of edits made, the last edited time and urgency score.

    @PostMapping("/notes/create")
    public String createNote(@ModelAttribute Note note) {
        /*Note note = new Note();
        note.setTitle(title);   
        note.setContent(content);
        
        note.setDueDate(dueDate);
        note.setEditCount(0);
        note.setLastEdited(LocalDateTime.now());
        note.setUrgencyScore(noteService.calculateUrgency(note));
        note.setTags(noteService.scanContentForTags(content));*/
        
        noteService.createNote(note);
        
        return "redirect:/notes";
    }

    // This method calls the edit-note page, bringing the note with the passed ID 

    @GetMapping("/notes/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        Note note = noteService.getNoteById(id);

        model.addAttribute("note", note);
        
        return "edit-note";
    }

    // This method updates the note with the given ID with all the new values received from the form.

    @PostMapping("/notes/edit")
    public String updateNote(@RequestParam Long id, @RequestParam String title, @RequestParam String content, @RequestParam(required = false) LocalDate dueDate) {
        Note existingNote = noteService.getNoteById(id);

        existingNote.setTitle(title);
        existingNote.setContent(content);
        existingNote.setDueDate(dueDate);
        existingNote.setEditCount(existingNote.getEditCount() + 1);
        existingNote.setLastEdited(LocalDateTime.now());
        existingNote.setUrgencyScore(noteService.calculateUrgency(existingNote));
        existingNote.setTags(noteService.scanContentForTags(content));

        noteService.updateNote(id, existingNote);

        return "redirect:/notes";
    }

    // This method deletes the note with the given ID

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteNote(id);

        return "redirect:/notes";
    }
}
