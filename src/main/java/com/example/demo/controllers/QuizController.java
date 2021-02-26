package com.example.demo.controllers;

import com.example.demo.dtos.PaginationDto;
import com.example.demo.dtos.QuizDto;
import com.example.demo.dtos.SortingDto;
import com.example.demo.mappers.QuizMapper;
import com.example.demo.models.Quiz;
import com.example.demo.services.QuizService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiOperation(value = "quizzes", tags = "Quiz Controller")
@RestController
@RequestMapping("quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuizMapper mapper;

    public QuizController(QuizService quizService, QuizMapper mapper) {
        this.quizService = quizService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Get sorted and paginated quizzes by filter")
    @GetMapping()
    public List<QuizDto> getQuizzes(
            @Valid QuizDto quizDto,
            @Valid SortingDto sortingDto,
            @Valid PaginationDto paginationDto) {

        return quizService.getQuizzes(mapper.toEntity(quizDto), sortingDto, paginationDto)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Create quiz")
    @PostMapping
    public QuizDto createQuiz(@RequestBody QuizDto quizDto) {
        Quiz quiz = quizService.createQuiz(mapper.toEntity(quizDto));
        return mapper.toDto(quiz);
    }

    @ApiOperation(value = "Update quiz")
    @PutMapping("{quizId}")
    public QuizDto updateQuiz(
            @RequestBody QuizDto quizDto,
            @PathVariable("quizId") Long quizId) {

        Quiz quiz = quizService.updateQuiz(quizId, mapper.toEntity(quizDto));
        return mapper.toDto(quiz);
    }

    @ApiOperation(value = "Delete quiz")
    @DeleteMapping("{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
    }
}
