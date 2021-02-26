package com.example.demo.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "PaginationDto Model")
public class PaginationDto {

    @ApiModelProperty(
            notes = "Number of page",
            name = "page",
            value = "Number of page",
            example = "1")
    @NotBlank
    private Integer page;

    @ApiModelProperty(
            notes = "Number of results per page",
            name = "resultsPerPage",
            value = "Number of results per page",
            example = "2")
    @NotBlank
    private Integer resultsPerPage;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
