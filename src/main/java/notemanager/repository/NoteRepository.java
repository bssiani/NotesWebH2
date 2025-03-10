package notemanager.repository;

import notemanager.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

// The JPA repository interface provides methods for performing CRUD operations on the notes.
public interface NoteRepository extends JpaRepository<Note, Long> {
}