package com.example.quiz_manager.specifications;

import com.example.quiz_manager.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class QuizWithStartDate implements Specification<Quiz> {
    private LocalDateTime startDate;

    public QuizWithStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (startDate == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        LocalDateTime one = startDate.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime two = startDate.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);

        return criteriaBuilder.between(root.get("startDate"), one, two);
    }
}
