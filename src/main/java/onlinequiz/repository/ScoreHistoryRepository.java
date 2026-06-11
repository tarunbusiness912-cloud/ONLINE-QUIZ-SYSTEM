package onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import onlinequiz.entity.ScoreHistory;

public interface ScoreHistoryRepository
        extends JpaRepository<ScoreHistory, Long> {
}