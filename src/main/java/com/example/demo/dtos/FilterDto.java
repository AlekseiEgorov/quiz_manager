package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;

public class FilterDto {
    @NotBlank
    private String name;
    @NotBlank
    private String isActivity;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity;
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
}
