package com.example.demo.specifications;

import com.example.demo.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QuizWithActivity implements Specification<Quiz> {
    private Boolean isActivity;

    public QuizWithActivity(String isActivityStr) {
        this.isActivity = "true".equalsIgnoreCase(isActivityStr) ? Boolean.TRUE :
                "false".equalsIgnoreCase(isActivityStr) ? Boolean.FALSE : null;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (isActivity == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("isActivity"), isActivity);
    }
}
