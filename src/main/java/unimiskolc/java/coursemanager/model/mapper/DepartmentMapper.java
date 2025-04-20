package unimiskolc.java.coursemanager.model.mapper;

import org.springframework.stereotype.Component;
import unimiskolc.java.coursemanager.model.dto.DepartmentDto;
import unimiskolc.java.coursemanager.model.entity.Department;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setBuilding(dto.getBuilding());
        department.setActive(dto.isActive());
        return department;
    }

    public DepartmentDto toDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCode(department.getCode());
        dto.setBuilding(department.getBuilding());
        dto.setActive(department.isActive());
        return dto;
    }

}
