package com.devstaff.farm_collector.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmDetailResponse {
    Long farmId;
    Long farmFieldId;
}
