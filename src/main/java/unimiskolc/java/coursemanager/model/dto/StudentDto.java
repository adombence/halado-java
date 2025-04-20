package unimiskolc.java.coursemanager.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class StudentDto {

    @Schema(description = "Unique identifier of the student")
    private UUID id;

    @NotBlank
    @Schema(description = "First name of the student", example = "John")
    private String firstName;

    @NotBlank
    @Schema(description = "Last name of the student", example = "Doe")
    private String lastName;

    @Email
    @NotBlank
    @Schema(description = "Email address of the student", example = "anna.nagy@example.com")
    private String email;

    @Schema(description = "Whether the student is currently active", example = "true")
    private boolean active;
}
