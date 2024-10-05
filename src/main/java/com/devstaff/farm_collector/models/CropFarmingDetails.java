package com.devstaff.farm_collector.models;

import com.devstaff.farm_collector.constants.Season;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CropFarmingDetails {
    @NotNull
    private Season season;

    private Double plantingAreaInAcres;
    private Double amountOfExpectedProduct;
    private Double actualAmountOfProductHarvested;
}
