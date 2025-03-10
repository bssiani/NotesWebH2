package notemanager.model;

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

    // Getters and setters for the id, title, and content fields
    
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
}