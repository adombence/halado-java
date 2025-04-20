package unimiskolc.java.coursemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unimiskolc.java.coursemanager.model.entity.Department;
import unimiskolc.java.coursemanager.model.exceptions.DepartmentNotFoundException;
import unimiskolc.java.coursemanager.persist.DepartmentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(UUID id, Department updatedDepartment) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found: " + id));

        department.setName(updatedDepartment.getName());
        department.setCode(updatedDepartment.getCode());
        department.setBuilding(updatedDepartment.getBuilding());
        department.setActive(updatedDepartment.isActive());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(UUID id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findByBuilding(String building) {
        return departmentRepository.findAll().stream()
                .filter(d -> d.getBuilding().toLowerCase().contains(building.toLowerCase()))
                .toList();
    }
}
