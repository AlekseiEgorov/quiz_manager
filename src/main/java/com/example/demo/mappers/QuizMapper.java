package com.example.demo.mappers;

import com.example.demo.dtos.QuizDto;
import com.example.demo.models.Question;
import com.example.demo.models.Quiz;
import com.example.demo.repositories.QuestionRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public interface QuizMapper {

    @Mapping(target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    QuizDto toDto(Quiz quiz);

    @Mapping(target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Quiz toEntity(QuizDto quizDto);

    default String map(Boolean isActive) {
        String result = isActive.toString();
        return result;
    }

    default Boolean map(String isActive) {
        return "true".equalsIgnoreCase(isActive) ? Boolean.TRUE :
                "false".equalsIgnoreCase(isActive) ? Boolean.FALSE : null;
    }

    default Set<String> mapFromQuestions(Set<Question> date) {
        return date.stream()
                .sorted((firstQuestion, secondQuestion) ->
                        firstQuestion.getDisplayOrder().compareTo(firstQuestion.getDisplayOrder()))
                .map(Question::getText)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default Set<Question> mapToQuestions(Set<String> texts) {
        if (texts == null) {
            return Collections.EMPTY_SET;
        }
        Set<Question> question = new LinkedHashSet<>();
        long displayOrder = 1;
        for (String text : texts) {
            question.add(new Question(text, displayOrder++));
        }
        return question;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuiz(Quiz dto, @MappingTarget Quiz entity);

    @AfterMapping
    default void after(@MappingTarget Quiz quiz) {
        for (Question question : quiz.getQuestions()) {
            question.setQuiz(quiz);
        }
    }

}