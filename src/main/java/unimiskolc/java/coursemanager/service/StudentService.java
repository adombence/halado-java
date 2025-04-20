package unimiskolc.java.coursemanager.service;

import unimiskolc.java.coursemanager.model.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(UUID id, Student student);
    void deleteStudent(UUID id);
    List<Student> getAllStudents();
    List<Student> findStudentsByName(String name);
}
