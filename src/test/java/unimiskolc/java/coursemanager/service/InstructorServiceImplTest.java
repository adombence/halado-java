package unimiskolc.java.coursemanager.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unimiskolc.java.coursemanager.model.entity.Instructor;
import unimiskolc.java.coursemanager.persist.InstructorRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    public InstructorServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createInstructor_ShouldSaveAndReturnEntity() {
        Instructor input = new Instructor();
        input.setId(null);

        Instructor saved = new Instructor();
        saved.setId(UUID.randomUUID());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(saved);

        Instructor result = instructorService.createInstructor(input, UUID.randomUUID());

        assertNotNull(result.getId());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    void deleteInstructor_ShouldCallRepository() {
        UUID id = UUID.randomUUID();
        doNothing().when(instructorRepository).deleteById(id);

        instructorService.deleteInstructor(id);

        verify(instructorRepository, times(1)).deleteById(id);
    }

    @Test
    void updateInstructor_ShouldFindAndSaveEntity() {
        UUID id = UUID.randomUUID();
        Instructor existing = new Instructor();
        existing.setId(id);

        Instructor updated = new Instructor();
        updated.setId(id);

        when(instructorRepository.findById(id)).thenReturn(Optional.of(existing));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(updated);

        Instructor result = instructorService.updateInstructor(id, updated, UUID.randomUUID());

        assertEquals(id, result.getId());
        verify(instructorRepository).findById(id);
        verify(instructorRepository).save(any(Instructor.class));
    }
}
