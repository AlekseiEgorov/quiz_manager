package com.example.quiz_manager.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "PaginationDto Model")
public class PaginationDto {

    @ApiModelProperty(
            notes = "Number of page",
            name = "page",
            value = "Number of page. Begin with 0")
    @NotBlank
    private Integer page;

    @ApiModelProperty(
            notes = "Number of results per page",
            name = "resultsPerPage",
            value = "Number of results per page")
    @NotBlank
    private Integer resultsPerPage;

}
