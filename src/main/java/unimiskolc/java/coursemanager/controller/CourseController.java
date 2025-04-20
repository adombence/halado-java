package unimiskolc.java.coursemanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.coursemanager.model.dto.CourseDto;
import unimiskolc.java.coursemanager.model.mapper.CourseMapper;
import unimiskolc.java.coursemanager.service.CourseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public CourseDto create(@RequestBody @Valid CourseDto dto) {
        var course = courseMapper.toEntity(dto);
        var saved = courseService.createCourse(course, dto.getInstructorId());

        return courseMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable UUID id, @RequestBody @Valid CourseDto dto) {
        var course = courseMapper.toEntity(dto);
        var updated = courseService.updateCourse(id, course, dto.getInstructorId());

        return courseMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        courseService.deleteCourse(id);
    }

    @GetMapping
    public List<CourseDto> getAll(@RequestParam(required = false) String title) {
        var courses = (title != null)
                ? courseService.findByTitle(title)
                : courseService.getAllCourses();

        return courses.stream().map(courseMapper::toDto).toList();
    }
}
