package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "text",
            unique = true
    )
    private String name;

    @Column(
            name = "start_date",
            nullable = false,
            columnDefinition = "timestamp"
    )
    private LocalDateTime startDate;

    @Column(
            name = "end_date",
            nullable = false,
            columnDefinition = "timestamp"
    )
    private LocalDateTime endDate;

    @Column(
            name = "is_active",
            nullable = false,
            columnDefinition = "boolean"
    )
    private Boolean isActive;

    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        Set<Question> sortedSet = new TreeSet<Question>((firstQuestion, secondQuestion) ->
                firstQuestion.getDisplayOrder().compareTo(firstQuestion.getDisplayOrder()));
        sortedSet.addAll(questions);
        this.questions = sortedSet;
    }

}
