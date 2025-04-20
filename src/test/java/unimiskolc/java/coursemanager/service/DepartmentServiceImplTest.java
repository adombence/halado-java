package unimiskolc.java.coursemanager.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import unimiskolc.java.coursemanager.model.entity.Department;
import unimiskolc.java.coursemanager.persist.DepartmentRepository;
import unimiskolc.java.coursemanager.persist.InstructorRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    public DepartmentServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDepartment_ShouldSaveAndReturnEntity() {
        Department input = new Department();
        input.setId(null);

        Department saved = new Department();
        saved.setId(UUID.randomUUID());

        when(departmentRepository.save(any(Department.class))).thenReturn(saved);

        Department result = departmentService.createDepartment(input);

        assertNotNull(result.getId());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void deleteDepartment_ShouldCallRepository() {
        UUID id = UUID.randomUUID();
        doNothing().when(departmentRepository).deleteById(id);

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    void updateDepartment_ShouldFindAndSaveEntity() {
        UUID id = UUID.randomUUID();
        Department existing = new Department();
        existing.setId(id);

        Department updated = new Department();
        updated.setId(id);

        when(departmentRepository.findById(id)).thenReturn(Optional.of(existing));
        when(departmentRepository.save(any(Department.class))).thenReturn(updated);

        Department result = departmentService.updateDepartment(id, updated);

        assertEquals(id, result.getId());
        verify(departmentRepository).findById(id);
        verify(departmentRepository).save(any(Department.class));
    }
}
