package ch.fhnw.webfr.flashcard.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;

@Component
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> {
	List<Questionnaire> findByTitle(String title);
}
