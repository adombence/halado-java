package unimiskolc.java.coursemanager.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import unimiskolc.java.coursemanager.model.entity.Course;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> { }
