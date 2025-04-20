package unimiskolc.java.coursemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import unimiskolc.java.coursemanager.model.exceptions.CourseNotFoundException;
import unimiskolc.java.coursemanager.model.exceptions.DepartmentNotFoundException;
import unimiskolc.java.coursemanager.model.exceptions.InstructorNotFoundException;
import unimiskolc.java.coursemanager.model.exceptions.StudentNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFound(StudentNotFoundException ex) {
        return buildResponse("Student Not Found", ex.getMessage());
    }

    @ExceptionHandler(InstructorNotFoundException.class)
    public ResponseEntity<Object> handleInstructorNotFound(InstructorNotFoundException ex) {
        return buildResponse("Instructor Not Found", ex.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException ex) {
        return buildResponse("Course Not Found", ex.getMessage());
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> handleDepartmentNotFound(DepartmentNotFoundException ex) {
        return buildResponse("Department Not Found", ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Failed");

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        body.put("message", "One or more fields failed validation.");
        body.put("fields", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
