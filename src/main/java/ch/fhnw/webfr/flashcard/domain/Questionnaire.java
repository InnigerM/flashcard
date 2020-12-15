package ch.fhnw.webfr.flashcard.domain;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questionnaires")
public class Questionnaire {
	@Id
	private String id;
	@Size(min = 2, max = 30)
	private String title;
	@Size(min = 10, max = 50)
	private String description;

	public Questionnaire(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
