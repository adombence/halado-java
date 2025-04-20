package unimiskolc.java.coursemanager.service;

import unimiskolc.java.coursemanager.model.entity.Instructor;

import java.util.List;
import java.util.UUID;

public interface InstructorService {
    Instructor createInstructor(Instructor instructor, UUID departmentId);
    Instructor updateInstructor(UUID id, Instructor instructor, UUID departmentId);
    void deleteInstructor(UUID id);
    List<Instructor> getAllInstructors();
    List<Instructor> findByDepartment(String departmentName);
    Instructor findById(UUID id);
}
