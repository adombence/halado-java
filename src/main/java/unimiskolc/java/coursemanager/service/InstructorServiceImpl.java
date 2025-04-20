package unimiskolc.java.coursemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unimiskolc.java.coursemanager.model.entity.Instructor;
import unimiskolc.java.coursemanager.model.exceptions.InstructorNotFoundException;
import unimiskolc.java.coursemanager.persist.InstructorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Override
    public Instructor findById(UUID id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found: " + id));
    }

    @Override
    public Instructor createInstructor(Instructor instructor, UUID departmentId) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(UUID id, Instructor updatedInstructor, UUID departmentId) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found: " + id));

        instructor.setFirstName(updatedInstructor.getFirstName());
        instructor.setLastName(updatedInstructor.getLastName());
        instructor.setEmail(updatedInstructor.getEmail());
        instructor.setPhone(updatedInstructor.getPhone());
        instructor.setFullTime(updatedInstructor.isFullTime());

        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(UUID id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public List<Instructor> findByDepartment(String departmentName) {
        return instructorRepository.findAll().stream()
                .filter(i -> i.getDepartment() != null &&
                        i.getDepartment().getName().toLowerCase().contains(departmentName.toLowerCase()))
                .toList();
    }
}
