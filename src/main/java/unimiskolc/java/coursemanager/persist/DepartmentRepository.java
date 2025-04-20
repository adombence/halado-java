package unimiskolc.java.coursemanager.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import unimiskolc.java.coursemanager.model.entity.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> { }
