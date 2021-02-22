package com.example.demo.specifications;

import com.example.demo.models.Quiz;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class QuizWithStartDate implements Specification<Quiz> {
    private String input;

    public QuizWithStartDate(String input) {
        this.input = input;
    }

    @Override
    public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (input == null || input.isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm",  Locale.US);
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        LocalDateTime one = dateTime.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime two = dateTime.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);

        return criteriaBuilder.between(root.get("startDate"), one, two);
    }
}
