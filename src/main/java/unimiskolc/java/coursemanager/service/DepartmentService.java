package unimiskolc.java.coursemanager.service;

import unimiskolc.java.coursemanager.model.entity.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department updateDepartment(UUID id, Department updatedDepartment);
    void deleteDepartment(UUID id);
    List<Department> getAllDepartments();
    List<Department> findByBuilding(String building);
}
