package onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinequiz.entity.ScoreHistory;
import onlinequiz.repository.ScoreHistoryRepository;

import java.util.List;

@Service
public class ScoreHistoryService {

    @Autowired
    private ScoreHistoryRepository repository;

    public void save(ScoreHistory history) {
        repository.save(history);
    }

    public List<ScoreHistory> getAllHistory() {
        return repository.findAll();
    }
}