package unimiskolc.java.coursemanager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean fullTime;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    @ManyToOne
    private Department department;
}
