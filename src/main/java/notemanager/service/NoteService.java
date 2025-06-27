package notemanager.service;

import notemanager.model.Note;
import notemanager.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// The @Slf4j annotation can be used to generate a logger field in the class, for debugging or errors.
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;

@Service
@Slf4j
public class NoteService {

    // The @Autowired annotation is used to inject the NoteRepository bean into the NoteService class
    // This way the CRUD operations (findAll, save, findBy and so on) can be performed on the notes
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note) {
        note.setEditCount(0);
        note.setLastEdited(LocalDateTime.now());
        note.setUrgencyScore(calculateUrgency(note));
        note.setTags(scanContentForTags(note.getContent()));

        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note updatedNote) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
        
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        note.setDueDate(updatedNote.getDueDate());
        note.setEditCount(note.getEditCount() + 1);
        note.setLastEdited(LocalDateTime.now());
        String detectedTags = scanContentForTags(note.getContent());
        note.setTags(detectedTags);
        
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }

        noteRepository.deleteById(id);
    }

    public double calculateUrgency(Note note) {
        double score = 0.0;

        // Deadline proximity calculation
        if (note.getDueDate() != null) {
            long daysUntilDue = ChronoUnit.DAYS.between(LocalDate.now(), note.getDueDate());
            score += Math.max(0, 10 - daysUntilDue);
        }

        // Recent activity calculation
        if (note.getLastEdited() != null && note.getLastEdited().isAfter(LocalDateTime.now().minusDays(2))) {
            score += 2.0;
        }

        return score;
    }
    
    public List<Note> getNotesSortedByUrgency() {
    return noteRepository.findAll().stream()
            .peek(note -> note.setUrgencyScore(calculateUrgency(note)))
            .sorted(Comparator.comparingDouble(Note::getUrgencyScore).reversed())
            .collect(Collectors.toList());
    }

    public List<Note> getNotesSortedByDate() {
        return noteRepository.findAllByOrderByLastEditedDesc();
    }

    public String scanContentForTags(String content) {
        if (content == null) return "";
        
        final String lowerContent = content.toLowerCase();
        Set<String> tags = new HashSet<>(); // Using Set to avoid duplicates
        
        Map<String, String> keywordMap = Map.of(
            "urgent", "urgent",
            "asap", "urgent",
            "immediately", "urgent",
            "important", "important",
            "priority", "important",
            "critical", "urgent",
            "eggs", "recipe"
        );
        
        // Checks if the content contains any keywords
        keywordMap.forEach((keyword, tag) -> {
            if (lowerContent.contains(keyword)) {
                tags.add(tag);
            }
        });
        
        return String.join(",", tags);
    }
}