package com.devstaff.farm_collector.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class FarmDetailResponse {
    Long farmId;
    Long farmFieldId;
}
