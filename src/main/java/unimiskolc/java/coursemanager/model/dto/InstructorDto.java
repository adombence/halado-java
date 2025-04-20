package unimiskolc.java.coursemanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class InstructorDto {

    @Schema(description = "Unique identifier of the instructor", example = "")
    private UUID id;

    @NotBlank
    @Schema(description = "First name of the instructor", example = "John")
    private String firstName;

    @NotBlank
    @Schema(description = "Last name of the instructor", example = "Smith")
    private String lastName;

    @Email
    @NotBlank
    @Schema(description = "Email address of the instructor", example = "john.smith@example.com")
    private String email;

    @Schema(description = "Phone number of the instructor", example = "+36 00 000 0000")
    private String phone;

    @NotNull
    @Schema(description = "Whether the instructor is full-time", example = "true")
    private boolean fullTime;

    @NotNull
    @Schema(description = "ID of the department the instructor belongs to", example = "")
    private UUID departmentId;
}
