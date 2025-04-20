package unimiskolc.java.coursemanager.model.mapper;

import org.springframework.stereotype.Component;
import unimiskolc.java.coursemanager.model.dto.CourseDto;
import unimiskolc.java.coursemanager.model.entity.Course;

@Component
public class CourseMapper {
    public Course toEntity(CourseDto dto) {
        Course course = new Course();

        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCredit(dto.getCredit());
        course.setAvailable(dto.isAvailable());

        // instructorId-t majd a Service réteg kezeli
        return course;
    }

    public CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();

        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setCredit(course.getCredit());
        dto.setAvailable(course.isAvailable());
        dto.setInstructorId(
            course.getInstructor() != null
                ? course.getInstructor().getId()
                : null
        );

        return dto;
    }

}
