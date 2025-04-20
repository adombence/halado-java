package unimiskolc.java.coursemanager.model.mapper;

import org.springframework.stereotype.Component;
import unimiskolc.java.coursemanager.model.dto.StudentDto;
import unimiskolc.java.coursemanager.model.entity.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentDto dto) {
        Student student = new Student();

        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setActive(dto.isActive());

        return student;
    }

    public StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setActive(student.isActive());

        return dto;
    }

}
