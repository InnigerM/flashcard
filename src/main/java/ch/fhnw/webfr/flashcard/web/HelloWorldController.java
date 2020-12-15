package ch.fhnw.webfr.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@RequestParam String name) {
        long numQuestionnaires = questionnaireRepository.count();

        return "<html>\n" + "<header><title>Welcome</title></header>\n" + "<body>" + "<p>Hello " + name + "</p>"
                + "<p>You have " + numQuestionnaires + " questionnaires in your repo</p>";
    }
}
