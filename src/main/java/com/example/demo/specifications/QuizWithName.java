package com.example.demo.specifications;

import com.example.demo.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QuizWithName implements Specification<Quiz> {
    private String name;


    public QuizWithName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (name == null || name.isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("name"), name);
    }
}
