package onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import onlinequiz.entity.Question;
import onlinequiz.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/add-question")
    public String showForm(Model model) {

        model.addAttribute("question", new Question());

        return "add-question";
    }

    @PostMapping("/save-question")
    public String saveQuestion(
            @ModelAttribute Question question) {

        service.saveQuestion(question);

        return "redirect:/add-question";
    }

    @GetMapping("/questions")
    public String viewQuestions(Model model) {

    model.addAttribute(
        "questions",
        service.getAllQuestions()
    );

    return "questions";
}


}
