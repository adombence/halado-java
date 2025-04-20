package unimiskolc.java.coursemanager.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import unimiskolc.java.coursemanager.model.entity.Instructor;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
}
