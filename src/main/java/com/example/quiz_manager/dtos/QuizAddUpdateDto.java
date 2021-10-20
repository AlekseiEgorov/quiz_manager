package com.example.quiz_manager.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ApiModel(description = "QuizAddUpdateDto Model")
public class QuizAddUpdateDto {

    @ApiModelProperty(notes = "Name of the quiz", name = "name")
    @NotBlank
    private String name;

    @ApiModelProperty(
            notes = "Start date of the quiz",
            name = "startDate",
            value = "Format: yyyy-MM-dd HH:mm:ss",
            example = "2020-03-08 04:05:06")
    @NotBlank
    private String startDate;

    @ApiModelProperty(
            notes = "End date of the quiz",
            name = "endDate",
            value = "Format: yyyy-MM-dd HH:mm:ss",
            example = "2020-05-08 04:05:06")
    @NotBlank
    private String endDate;

    @ApiModelProperty(
            notes = "Is the quiz active?",
            name = "isActive",
            value = "true or false",
            example = "true",
            allowableValues = "true, false")
    @NotBlank
    private String isActive;

    @ApiModelProperty(
            notes = "Questions for the quiz",
            name = "questions",
            example = "[\"Первый вопрос\",\"Второй вопрос\"]")
    private Set<String> questions;

}
