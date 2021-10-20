package com.example.quiz_manager.specifications;

import com.example.quiz_manager.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QuizWithActivity implements Specification<Quiz> {
    private Boolean isActive;

    public QuizWithActivity(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (isActive == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
