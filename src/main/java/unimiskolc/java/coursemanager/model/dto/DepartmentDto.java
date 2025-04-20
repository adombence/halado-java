package unimiskolc.java.coursemanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDto {

    @Schema(description = "Unique identifier of the department")
    private UUID id;

    @NotBlank
    @Schema(description = "Name of the department", example = "Computer Science")
    private String name;

    @NotBlank
    @Schema(description = "Short code identifier for the department", example = "CS")
    private String code;

    @Schema(description = "Building where the department is located", example = "INF")
    private String building;

    @Schema(description = "Whether the department is currently active", example = "true")
    private boolean active;
}
