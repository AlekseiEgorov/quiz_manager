package com.example.quiz_manager.specifications;

import com.example.quiz_manager.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QuizWithId implements Specification<Quiz> {
    private Long id;

    public QuizWithId(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (id == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("id"), id);
    }
}
