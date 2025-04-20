package unimiskolc.java.coursemanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.coursemanager.model.dto.DepartmentDto;
import unimiskolc.java.coursemanager.model.mapper.DepartmentMapper;
import unimiskolc.java.coursemanager.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @PostMapping
    public DepartmentDto create(@RequestBody @Valid DepartmentDto dto) {
        var department = departmentMapper.toEntity(dto);
        var saved = departmentService.createDepartment(department);
        return departmentMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public DepartmentDto update(@PathVariable UUID id, @RequestBody @Valid DepartmentDto dto) {
        var department = departmentMapper.toEntity(dto);
        var updated = departmentService.updateDepartment(id, department);
        return departmentMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping
    public List<DepartmentDto> getAll(@RequestParam(required = false) String building) {
        var departments = (building != null)
                ? departmentService.findByBuilding(building)
                : departmentService.getAllDepartments();
        return departments.stream().map(departmentMapper::toDto).toList();
    }
}
