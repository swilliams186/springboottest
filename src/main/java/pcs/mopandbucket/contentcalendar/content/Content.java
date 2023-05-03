package pcs.mopandbucket.contentcalendar.content;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public record Content(
	Integer id,
	@NotBlank
	String title,
	String desc,
	Status status,
	Type contentType,
	@PastOrPresent
	LocalDateTime dateCreated,
	LocalDateTime dateUpdated,
	String url
) {}
