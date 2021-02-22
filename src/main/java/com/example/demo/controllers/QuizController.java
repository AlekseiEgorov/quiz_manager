package com.example.demo.controllers;

import com.example.demo.dtos.FilterDto;
import com.example.demo.dtos.PaginationDto;
import com.example.demo.dtos.QuizDto;
import com.example.demo.dtos.SortingDto;
import com.example.demo.mappers.QuizMapper;
import com.example.demo.models.Quiz;
import com.example.demo.services.QuizService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping()
    public List<QuizDto> getQuizzes(
            @Valid FilterDto filterDto,
            @Valid SortingDto sortingDto,
            @Valid PaginationDto paginationDto) {

        return quizService.getQuizzes(filterDto, sortingDto, paginationDto)
                .stream()
                .map(QuizMapper.INSTANCE::quizToQuizDto)
                .collect(Collectors.toList());

    }

//    @PostMapping
//    public void createQuiz(QuizDto quiz) {
//
//    }

    @DeleteMapping("{quizId}")
    public void deleteStudent(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
    }
}
