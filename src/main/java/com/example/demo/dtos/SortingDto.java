package com.example.demo.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;

public class SortingDto {

    @NotBlank
    private String sortedBy;
    @NotBlank
    private Sort.Direction orderBy;

    public String getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }

    public Sort.Direction getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
       this.orderBy = Sort.Direction.fromString(orderBy);
    }
}
