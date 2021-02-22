package com.example.demo.services;

import com.example.demo.dtos.FilterDto;
import com.example.demo.dtos.PaginationDto;
import com.example.demo.dtos.SortingDto;
import com.example.demo.models.Quiz;
import com.example.demo.repositories.QuizRepository;
import com.example.demo.specifications.QuizWithActivity;
import com.example.demo.specifications.QuizWithEndDate;
import com.example.demo.specifications.QuizWithName;
import com.example.demo.specifications.QuizWithStartDate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getQuizzes(FilterDto filterDto, SortingDto sortingDto, PaginationDto paginationDto) {

        Specification<Quiz> specification = Specification
                .where(new QuizWithName(filterDto.getName()))
                .and(new QuizWithStartDate(filterDto.getStartDate()))
                .and(new QuizWithEndDate(filterDto.getEndDate()))
                .and(new QuizWithActivity(filterDto.getIsActivity()));

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
}
