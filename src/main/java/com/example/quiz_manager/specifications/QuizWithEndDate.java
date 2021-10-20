package com.example.quiz_manager.specifications;

import com.example.quiz_manager.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class QuizWithEndDate implements Specification<Quiz> {
    private LocalDateTime endDate;

    public QuizWithEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (endDate == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        LocalDateTime one = endDate.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime two = endDate.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);

        return criteriaBuilder.between(root.get("endDate"), one, two);
    }
}
