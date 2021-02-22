package com.example.demo.mappers;

import com.example.demo.dtos.QuizDto;
import com.example.demo.models.Question;
import com.example.demo.models.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface QuizMapper {

    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizDto quizToQuizDto(Quiz quiz);

    default Set<String> map(Set<Question> date) {
        return date.stream()
                .map(Question::getText)
                .collect(Collectors.toSet());
    }
}