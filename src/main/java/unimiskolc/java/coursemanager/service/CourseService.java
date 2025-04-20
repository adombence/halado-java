package unimiskolc.java.coursemanager.service;

import unimiskolc.java.coursemanager.model.entity.Course;
import unimiskolc.java.coursemanager.model.entity.Student;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course createCourse(Course course, UUID instructorId);
    Course updateCourse(UUID id, Course course, UUID instructorId);
    void deleteCourse(UUID course);
    List<Course> getAllCourses();
    List<Course> findByTitle(String title);
}
