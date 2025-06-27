package notemanager.repository;

import notemanager.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

// The JPA repository interface provides methods for performing CRUD operations on the notes.
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n ORDER BY n.lastEdited DESC")
    List<Note> findAllByOrderByLastEditedDesc();
}