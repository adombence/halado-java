package unimiskolc.java.coursemanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    private UUID id;

    @NotBlank
    @Schema(description = "Title of the course", example = "Haladó Java")
    private String title;

    @Schema(description = "Description of the course content")
    private String description;

    @NotNull
    @Schema(description = "Credit value of the course", example = "5")
    private int credit;

    @Schema(description = "Whether the course is available", example = "true")
    private boolean available;

    @NotNull
    private UUID instructorId;
}