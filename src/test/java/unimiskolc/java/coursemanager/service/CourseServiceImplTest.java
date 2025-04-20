package unimiskolc.java.coursemanager.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unimiskolc.java.coursemanager.model.entity.Course;
import unimiskolc.java.coursemanager.model.entity.Instructor;
import unimiskolc.java.coursemanager.persist.CourseRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private CourseServiceImpl courseService;

    public CourseServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCourse_ShouldSaveAndReturnEntity() {
        UUID instructorId = UUID.randomUUID();

        Course input = new Course();
        input.setId(null);

        Instructor instructor = new Instructor();
        instructor.setId(instructorId);
        input.setInstructor(instructor); // csak ha már inicializáltad

        Course saved = new Course();
        saved.setId(UUID.randomUUID());

        when(instructorService.findById(instructorId)).thenReturn(instructor);
        when(courseRepository.save(any(Course.class))).thenReturn(saved);

        Course result = courseService.createCourse(input, instructorId);

        assertNotNull(result.getId());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void deleteCourse_ShouldCallRepository() {
        UUID id = UUID.randomUUID();
        doNothing().when(courseRepository).deleteById(id);

        courseService.deleteCourse(id);

        verify(courseRepository, times(1)).deleteById(id);
    }

    @Test
    void updateCourse_ShouldFindAndSaveEntity() {
        UUID id = UUID.randomUUID();
        Course existing = new Course();
        existing.setId(id);

        Course updated = new Course();
        updated.setId(id);

        when(courseRepository.findById(id)).thenReturn(Optional.of(existing));
        when(courseRepository.save(any(Course.class))).thenReturn(updated);

        Course result = courseService.updateCourse(id, updated, UUID.randomUUID());

        assertEquals(id, result.getId());
        verify(courseRepository).findById(id);
        verify(courseRepository).save(any(Course.class));
    }
}
