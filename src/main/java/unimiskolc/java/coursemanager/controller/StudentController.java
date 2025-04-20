package unimiskolc.java.coursemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.coursemanager.model.dto.StudentDto;
import unimiskolc.java.coursemanager.model.entity.Student;
import unimiskolc.java.coursemanager.model.mapper.StudentMapper;
import unimiskolc.java.coursemanager.persist.StudentRepository;
import unimiskolc.java.coursemanager.service.StudentServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    public StudentDto create(@RequestBody @Valid StudentDto dto) {
        var student = studentMapper.toEntity(dto);
        var saved = studentService.createStudent(student);

        return studentMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable UUID id, @RequestBody @Valid StudentDto dto) {
        var student = studentMapper.toEntity(dto);
        var updated = studentService.updateStudent(id, student);
        return studentMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public List<StudentDto> getAll(@RequestParam(required = false) String name) {
        var students = (name != null)
                ? studentService.findStudentsByName(name)
                : studentService.getAllStudents();

        return students.stream().map(studentMapper::toDto).toList();
    }
}
