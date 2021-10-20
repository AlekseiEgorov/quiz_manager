package com.example.quiz_manager.controllers;

import com.example.quiz_manager.dtos.PaginationDto;
import com.example.quiz_manager.dtos.QuizAddUpdateDto;
import com.example.quiz_manager.dtos.QuizDto;
import com.example.quiz_manager.dtos.SortingDto;
import com.example.quiz_manager.exceptions.ApiRequestException;
import com.example.quiz_manager.mappers.QuizMapper;
import com.example.quiz_manager.models.Quiz;
import com.example.quiz_manager.services.QuizService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;
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
    public QuizDto createQuiz(@RequestBody QuizAddUpdateDto quizAddUpdateDto) {
        Quiz quiz = quizService.createQuiz(mapper.toEntity(quizAddUpdateDto));
        return mapper.toDto(quiz);
    }

    @ApiOperation(value = "Update quiz")
    @PutMapping("{quizId}")
    public QuizDto updateQuiz(
            @RequestBody QuizAddUpdateDto quizAddUpdateDto,
            @PathVariable("quizId") Long quizId) {

        Quiz quiz = quizService.updateQuiz(quizId, mapper.toEntity(quizAddUpdateDto));
        return mapper.toDto(quiz);
    }

    @ApiOperation(value = "Delete quiz")
    @DeleteMapping("{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
    }

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleApiRequestException(
            ApiRequestException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDateTimeParseException(
            DateTimeParseException exception
    ) {
        String enteredText = exception.getMessage().split("'")[1];
        String formatErrorText = "Entered text '%s' does not match format yyyy-MM-dd HH:mm:ss";
        String errorText = String.format(formatErrorText, enteredText);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorText);
    }
}
