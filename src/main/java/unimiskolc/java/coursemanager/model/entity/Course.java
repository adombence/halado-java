package unimiskolc.java.coursemanager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    private String title;
    private String description;
    private int credit;
    private boolean available;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    private Instructor instructor;
}
