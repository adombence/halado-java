package unimiskolc.java.coursemanager.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unimiskolc.java.coursemanager.model.entity.Student;
import unimiskolc.java.coursemanager.persist.StudentRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    public StudentServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudent_ShouldSaveAndReturnEntity() {
        Student input = new Student();
        input.setId(null);

        Student saved = new Student();
        saved.setId(UUID.randomUUID());

        when(studentRepository.save(any(Student.class))).thenReturn(saved);

        Student result = studentService.createStudent(input);

        assertNotNull(result.getId());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void deleteStudent_ShouldCallRepository() {
        UUID id = UUID.randomUUID();
        doNothing().when(studentRepository).deleteById(id);

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void updateStudent_ShouldFindAndSaveEntity() {
        UUID id = UUID.randomUUID();
        Student existing = new Student();
        existing.setId(id);

        Student updated = new Student();
        updated.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(existing));
        when(studentRepository.save(any(Student.class))).thenReturn(updated);

        Student result = studentService.updateStudent(id, updated);

        assertEquals(id, result.getId());
        verify(studentRepository).findById(id);
        verify(studentRepository).save(any(Student.class));
    }
}
