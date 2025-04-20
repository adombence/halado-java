package unimiskolc.java.coursemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unimiskolc.java.coursemanager.model.exceptions.StudentNotFoundException;
import unimiskolc.java.coursemanager.model.entity.Student;
import unimiskolc.java.coursemanager.persist.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(UUID id, Student updatedStudent) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));


        existing.setFirstName(updatedStudent.getFirstName());
        existing.setLastName(updatedStudent.getLastName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setActive(updatedStudent.isActive());

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(s ->
                        s.getFirstName().equalsIgnoreCase(name) ||
                        s.getLastName().equalsIgnoreCase(name)
                ).toList();
    }
}
