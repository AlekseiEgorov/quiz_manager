package com.example.quiz_manager.dtos;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "SortingDto Model")
public class SortingDto {

    @ApiModelProperty(
            notes = "Name of the field by which sorting",
            name = "sortedBy",
            value = "Name of the field by which sorting",
            allowableValues = "id, name, startDate, endDate")
    @NotBlank
    private String sortedBy;

    @ApiModelProperty(
            notes = "Sorting order",
            name = "orderBy",
            value = "Sorting order",
            allowableValues = "ASC, DESC")
    @Setter(AccessLevel.NONE)
    private Sort.Direction orderBy;

    public void setOrderBy(String orderBy) {
       this.orderBy = Sort.Direction.fromString(orderBy);
    }
}
