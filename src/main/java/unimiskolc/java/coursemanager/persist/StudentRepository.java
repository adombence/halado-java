package unimiskolc.java.coursemanager.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import unimiskolc.java.coursemanager.model.entity.Student;

import java.util.UUID;

public interface StudentRepository  extends JpaRepository<Student, UUID> {
}
