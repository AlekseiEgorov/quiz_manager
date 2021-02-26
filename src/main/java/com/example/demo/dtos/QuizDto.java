package com.example.demo.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@ApiModel(description = "QuizDto Model")
public class QuizDto {
    @ApiModelProperty(notes = "Id of the quiz", name = "id")
    @NotBlank
    private Long id;

    @ApiModelProperty(notes = "Name of the quiz", name = "name", example = "Уровень физической подготовки")
    @NotBlank
    private String name;

    @ApiModelProperty(
            notes = "Start date of the quiz",
            name = "startDate",
            value = "Format: yyyy-MM-dd HH:mm:ss",
            example = "2020-01-08 04:05:06")
    @NotBlank
    private String startDate;

    @ApiModelProperty(
            notes = "End date of the quiz",
            name = "endDate",
            value = "Format: yyyy-MM-dd HH:mm:ss",
            example = "2020-03-08 04:05:06")
    @NotBlank
    private String endDate;

    @ApiModelProperty(
            notes = "Is the quiz active?",
            name = "endDate",
            value = "true or false",
            example = "true")
    @NotBlank
    private String isActive;

    @ApiModelProperty(notes = "Questions for the quiz", name = "questions", example = "Сколько раз подтягиваетесь?")
    @NotBlank
    private Set<String> questions;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Set<String> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<String> questions) {
        this.questions = questions;
    }
}
