package notemanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

// Here I define the Note entity, which will be used to create the notes table in the database with id, title, and content.

@Entity
public class Note {

    // The @Id annotation is used to specify the primary key of the entity
    // The @GeneratedValue annotation is used to specify the generation strategy for the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String content;

    private LocalDate dueDate;
    private int editCount;
    private LocalDateTime lastEdited;
    private double urgencyScore;
    private String tags;

    // Getters and setters for all the variables
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getEditCount() {
        return editCount;
    }

    public void setEditCount(int editCount) {
        this.editCount = editCount;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public double getUrgencyScore() {
        return urgencyScore;
    }

    public void setUrgencyScore(double urgencyScore) {
        this.urgencyScore = urgencyScore;
    }
}