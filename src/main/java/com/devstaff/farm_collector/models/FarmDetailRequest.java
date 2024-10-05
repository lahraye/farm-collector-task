package com.devstaff.farm_collector.models;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class FarmDetailRequest {
    @NotNull(message = "farmId is required")
    Long farmId;

    Set<@Valid FarmDetail> farmDetail;
}
