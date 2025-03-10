package notemanager.controller;

import notemanager.model.Note;
import notemanager.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// This class is a controller that handles the requests and returns the responses to the /notes URL.

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    // When a HTTP GET request is received to to the /notes URL, @GetMapping will invoke this method
    // The method getAllNotes retrieves all notes from the database and adds them to the model, which passes the data to the view.
    
    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "view-notes";
    }

    // This request will redirect to the form to create a new note

    @GetMapping("/notes/create")
    public String showCreateForm() {
        return "create-note";
    }

    // When a POST request is received to the /notes/create URL, PostMapping will do the same and invoke this method.
    // The method createNote creates a new note with the title and content received from the form

    @PostMapping("/notes/create")
    public String createNote(@RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.createNote(note);
        return "redirect:/notes";
    }

    // This method retrieves the note with the given ID and adds it to the model

    @GetMapping("/notes/edit")
    public String showEditForm(@RequestParam Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "edit-note";
    }

    // This method updates the note with the given ID, new title and content received from the form

    @PostMapping("/notes/edit")
    public String updateNote(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        Note updatedNote = new Note();
        updatedNote.setTitle(title);
        updatedNote.setContent(content);
        noteService.updateNote(id, updatedNote);
        return "redirect:/notes";
    }

    // This method deletes the note with the given ID

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}