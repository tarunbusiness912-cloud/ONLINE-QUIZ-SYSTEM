package onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import onlinequiz.entity.Question;
import onlinequiz.service.QuestionService;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

import onlinequiz.entity.ScoreHistory;
import onlinequiz.service.ScoreHistoryService;

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

    @GetMapping("/start-quiz")
    public String startQuiz(Model model) {

    model.addAttribute(
        "questions",
        service.getAllQuestions()
    );

    return "quiz";
}

    @PostMapping("/submit-quiz")
public String submitQuiz(
        @RequestParam Map<String,String> answers,
        Model model) {

    List<Question> questions =
            service.getAllQuestions();

    int score = 0;

    for (Question q : questions) {

        String selectedAnswer =
                answers.get(String.valueOf(q.getId()));

        System.out.println("Question ID = " + q.getId());
        System.out.println("Selected = " + selectedAnswer);
        System.out.println("Correct  = " + q.getCorrectAnswer());

        if (selectedAnswer != null &&
            selectedAnswer.equalsIgnoreCase(
                    q.getCorrectAnswer())) {

            score++;
        }
    }
    ScoreHistory history = new ScoreHistory();
    history.setUsername("admin");
    history.setScore(score);
    history.setTotal(questions.size());
    history.setAttemptDate(LocalDateTime.now());
    historyService.save(history);


    model.addAttribute("score", score);
    model.addAttribute("total", questions.size());

    return "result";
}

    @GetMapping("/delete-question/{id}")
public String deleteQuestion(
        @PathVariable Long id) {

    service.deleteQuestion(id);

    return "redirect:/questions";
}
     
    @GetMapping("/edit-question/{id}")
public String editQuestion(
        @PathVariable Long id,
        Model model) {

    Question question =
            service.getQuestionById(id);

    model.addAttribute(
            "question",
            question);

    return "edit-question";
}
    @PostMapping("/update-question")
public String updateQuestion(
        @ModelAttribute Question question) {

    service.saveQuestion(question);

    return "redirect:/questions";
}
    @GetMapping("/categories")
public String categories() {
    return "categories";
}
    @GetMapping("/start-quiz/{category}")
public String startQuizByCategory(
        @PathVariable String category,
        Model model) {

    model.addAttribute(
            "questions",
            service.getQuestionsByCategory(category));

    return "quiz";
}
    @Autowired
private ScoreHistoryService historyService;

    
    @GetMapping("/history")
public String history(Model model) {

    model.addAttribute(
            "history",
            historyService.getAllHistory());

    return "history";
}
}