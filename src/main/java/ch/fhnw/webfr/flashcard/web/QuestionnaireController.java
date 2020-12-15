package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) throws IOException {
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        model.addAttribute("questionnaires", questionnaires);
        return "questionnaires/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable String id, Model model) throws IOException {
        Optional<Questionnaire> optionalQuestionnaire = questionnaireRepository.findById(id);
        Questionnaire questionnaire = null;
        if (optionalQuestionnaire.isPresent()) {
            questionnaire = optionalQuestionnaire.get();
        } else {
            return "404";
        }
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaires/show";
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String createQuestionnaire(Model model) {
        Questionnaire newQuestionnaire = new Questionnaire("", "");
        model.addAttribute("questionnaire", newQuestionnaire);
        return "questionnaires/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Questionnaire questionnaire, BindingResult result) {
        if (result.hasErrors()) {
            return "questionnaires/create";
        }
        questionnaireRepository.insert(questionnaire);
        return "redirect:questionnaires";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {
        if (questionnaireRepository.existsById(id)) {
            questionnaireRepository.deleteById(id);
            return "redirect:/questionnaires";
        }
        return "404";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "update")
    public String getUpdateForm(@PathVariable String id, Model model) {
        if (questionnaireRepository.existsById(id)) {
            Questionnaire questionnaire = questionnaireRepository.findById(id).get();
            model.addAttribute("questionnaire", questionnaire);
            return "questionnaires/update";
        } else {
            return "404";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable String id, @Valid Questionnaire questionnaire, BindingResult result) {
        if(result.hasErrors()){
            return "questionnaires/update";
        }
        questionnaireRepository.save(questionnaire);
        return "redirect:/questionnaires";
    }
}
