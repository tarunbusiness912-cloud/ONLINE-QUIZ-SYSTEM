package onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinequiz.entity.Question;
import onlinequiz.repository.QuestionRepository;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public void saveQuestion(Question question) {
        repository.save(question);
    }

public List<Question> getAllQuestions() {
    return repository.findAll();
}

}