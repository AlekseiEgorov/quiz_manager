package com.example.quiz_manager.mappers;

import com.example.quiz_manager.dtos.QuizAddUpdateDto;
import com.example.quiz_manager.dtos.QuizDto;
import com.example.quiz_manager.models.Question;
import com.example.quiz_manager.models.Quiz;
import org.mapstruct.*;

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
    Quiz toEntity(QuizDto quizDTO);

    @Mapping(target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Quiz toEntity(QuizAddUpdateDto quizAddUpdateDto);

    default String map(Boolean isActive) {
        String result = isActive.toString();
        return result;
    }

    default Boolean map(String isActive) {
        return "true".equalsIgnoreCase(isActive) ? Boolean.TRUE :
                "false".equalsIgnoreCase(isActive) ? Boolean.FALSE : null;
    }

    default Set<String> mapFromQuestions(Set<Question> questions) {
        if (questions == null) {
            return null;
        }
        return questions.stream()
                .sorted((firstQuestion, secondQuestion) ->
                        firstQuestion.getDisplayOrder().compareTo(firstQuestion.getDisplayOrder()))
                .map(Question::getText)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    default Set<Question> mapToQuestions(Set<String> texts) {
        if (texts == null) {
            return null;
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
        if (quiz.getQuestions() != null) {
            for (Question question : quiz.getQuestions()) {
                question.setQuiz(quiz);
            }
        }
    }

}