package com.devstaff.farm_collector.models;

import com.devstaff.farm_collector.entities.Crop;
import com.devstaff.farm_collector.validator.ValidCropFarmingDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class FarmFieldDetail {
    @NotNull
    private Long cropId;

    private List<@ValidCropFarmingDetail CropFarmingDetails> cropFarmingDetails;

    @JsonIgnore
    private Crop crop;
}