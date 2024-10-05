package com.devstaff.farm_collector.models;

import com.devstaff.farm_collector.constants.Season;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FarmDetail {
    @NotNull
    private Long cropId;
    @NotNull
    private Season season;

    private Long plantingAreaInAcres;
    private Double amountOfExpectedProduct;
    private Double actualAmountOfProductHarvested;
}
