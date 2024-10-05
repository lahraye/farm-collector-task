package com.devstaff.farm_collector.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class FarmDetailRequest {
    @NotNull(message = "farmId is required")
    Long farmFieldId;

    // I can add validation here to ensure the year is not in the future
    @Min(value = 1999)
    Integer year;

    @NotNull
    Set<FarmFieldDetail> farmFieldDetails;
}
