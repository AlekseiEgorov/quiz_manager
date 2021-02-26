package com.example.demo.dtos;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "SortingDto Model")
public class SortingDto {

    @ApiModelProperty(
            notes = "Name of the field by which sorting",
            name = "sortedBy",
            value = "Name of the field by which sorting",
            example = "startDate",
            allowableValues = "id, name, startDate, endDate")
    @NotBlank
    private String sortedBy;

    @ApiModelProperty(
            notes = "Sorting order",
            name = "orderBy",
            value = "Sorting order",
            example = "ASC",
            allowableValues = "ASC, DESC")
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
