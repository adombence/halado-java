package unimiskolc.java.coursemanager.model.mapper;

import org.springframework.stereotype.Component;
import unimiskolc.java.coursemanager.model.dto.InstructorDto;
import unimiskolc.java.coursemanager.model.entity.Instructor;

@Component
public class InstructorMapper {

    public Instructor toEntity(InstructorDto dto) {
        Instructor instructor = new Instructor();

        instructor.setId(dto.getId());
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setEmail(dto.getEmail());
        instructor.setPhone(dto.getPhone());
        instructor.setFullTime(dto.isFullTime());

        // departmentId-t majd a service-ben kezeled
        return instructor;
    }

    public InstructorDto toDto(Instructor instructor) {
        InstructorDto dto = new InstructorDto();

        dto.setId(instructor.getId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setEmail(instructor.getEmail());
        dto.setPhone(instructor.getPhone());
        dto.setFullTime(instructor.isFullTime());
        dto.setDepartmentId(
            instructor.getDepartment() != null
                ? instructor.getDepartment().getId()
                : null
        );

        return dto;
    }

}
