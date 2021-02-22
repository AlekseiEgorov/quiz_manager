package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;

public class PaginationDto {
    @NotBlank
    private Integer page;
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
