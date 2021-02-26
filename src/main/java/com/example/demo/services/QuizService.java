package com.example.demo.services;

import com.example.demo.dtos.PaginationDto;
import com.example.demo.dtos.QuizDto;
import com.example.demo.dtos.SortingDto;
import com.example.demo.mappers.QuizMapper;
import com.example.demo.models.Quiz;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.QuizRepository;
import com.example.demo.specifications.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizMapper mapper;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, QuizMapper mapper) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    public List<Quiz> getQuizzes(Quiz quizDto, SortingDto sortingDto, PaginationDto paginationDto) {

        Specification<Quiz> specification = Specification
                .where(new QuizWithName(quizDto.getName()))
                .and(new QuizWithStartDate(quizDto.getStartDate()))
                .and(new QuizWithEndDate(quizDto.getEndDate()))
                .and(new QuizWithActivity(quizDto.getIsActive()))
                .and(new QuizWithId(quizDto.getId()));

        boolean isSorting = sortingDto.getSortedBy() != null && sortingDto.getOrderBy() != null;
        boolean isPagination = paginationDto.getPage() != null && paginationDto.getResultsPerPage() != null;

        if (isSorting && isPagination) {
            Pageable pageable = PageRequest.of(
                    paginationDto.getPage(),
                    paginationDto.getResultsPerPage(),
                    Sort.by(sortingDto.getOrderBy(), sortingDto.getSortedBy()));
            return quizRepository.findAll(specification, pageable).getContent();
        }

        if (isSorting && !isPagination) {
            return quizRepository.findAll(specification, Sort.by(sortingDto.getOrderBy(), sortingDto.getSortedBy()));
        }

        if (isPagination && !isSorting) {
            Pageable pageable = PageRequest.of(
                    paginationDto.getPage(),
                    paginationDto.getResultsPerPage());
            return quizRepository.findAll(specification, pageable).getContent();
        }

        return quizRepository.findAll(specification);
    }

    public Quiz createQuiz(Quiz quiz) {
        if (quiz.getName() == null) {
            throw new IllegalStateException("No name entered for quiz");
        }
        if (quiz.getStartDate() == null) {
            throw new IllegalStateException("No startDate entered for quiz");
        }
        if (quiz.getEndDate() == null) {
            throw new IllegalStateException("No endDate entered for quiz");
        }
        if (quiz.getIsActive() == null) {
            throw new IllegalStateException("No isActive entered for quiz");
        }
        if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
            throw new IllegalStateException("No questions entered for quiz");
        }
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new IllegalStateException("Quiz with id " + quizId + " does not exist");
        }
        quizRepository.deleteById(quizId);
    }

    @Transactional
    public Quiz updateQuiz(Long quizId, Quiz quizChanges) {
        Quiz quiz = quizRepository.findById(quizId).
                orElseThrow(() -> new IllegalStateException(
                        "quiz with id " + quizId + " does not exist"
                ));
        if (!quizChanges.getQuestions().isEmpty()) {
            questionRepository.deleteAll(quiz.getQuestions());
        }
        mapper.updateQuiz(quizChanges, quiz);

        return quiz;
    }
}
