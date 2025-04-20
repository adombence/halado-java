package unimiskolc.java.coursemanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.coursemanager.model.dto.InstructorDto;
import unimiskolc.java.coursemanager.model.mapper.InstructorMapper;
import unimiskolc.java.coursemanager.service.InstructorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;

    @PostMapping
    public InstructorDto create(@RequestBody @Valid InstructorDto dto) {
        var instructor = instructorMapper.toEntity(dto);
        var saved = instructorService.createInstructor(instructor, dto.getDepartmentId());
        return instructorMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public InstructorDto update(@PathVariable UUID id, @RequestBody @Valid InstructorDto dto) {
        var instructor = instructorMapper.toEntity(dto);
        var updated = instructorService.updateInstructor(id, instructor, dto.getDepartmentId());
        return instructorMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        instructorService.deleteInstructor(id);
    }

    @GetMapping
    public List<InstructorDto> getAll(@RequestParam(required = false) String departmentName) {
        var instructors = (departmentName != null)
                ? instructorService.findByDepartment(departmentName)
                : instructorService.getAllInstructors();
        return instructors.stream().map(instructorMapper::toDto).toList();
    }
}
