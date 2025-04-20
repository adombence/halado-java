package unimiskolc.java.coursemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unimiskolc.java.coursemanager.model.entity.Course;
import unimiskolc.java.coursemanager.model.entity.Instructor;
import unimiskolc.java.coursemanager.model.exceptions.CourseNotFoundException;
import unimiskolc.java.coursemanager.persist.CourseRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    @Override
    public Course createCourse(Course course, UUID instructorId) {
        Instructor instructor = instructorService.findById(instructorId);
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(UUID courseId, Course updatedCourse, UUID instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + courseId));

        Instructor instructor = instructorService.findById(instructorId);

        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setCredit(updatedCourse.getCredit());
        course.setAvailable(updatedCourse.isAvailable());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByTitle(String title) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}
