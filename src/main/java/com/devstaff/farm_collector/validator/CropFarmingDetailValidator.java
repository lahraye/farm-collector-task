package com.devstaff.farm_collector.validator;

import com.devstaff.farm_collector.exceptions.CustomException;
import com.devstaff.farm_collector.models.CropFarmingDetails;
import com.devstaff.farm_collector.models.FarmFieldDetail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class CropFarmingDetailValidator implements ConstraintValidator<ValidCropFarmingDetail, CropFarmingDetails> {

    private String validationErrorMessage;
    @Override
    public void initialize(ValidCropFarmingDetail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CropFarmingDetails cropFarmingDetails, ConstraintValidatorContext context) {
        var season = cropFarmingDetails.getSeason();
        
        var validationErrorMessage = switch(season){
            case SPRING -> validateFarmPlantingSeasonRequest(cropFarmingDetails);
            case FALL -> validateFarmHarvestingSeasonRequest(cropFarmingDetails);
            default -> throw new CustomException("Unknown Season value");
        };

        if(!StringUtils.hasText(validationErrorMessage)){
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(validationErrorMessage).addConstraintViolation();
        return false;
    }

    private String validateFarmHarvestingSeasonRequest(CropFarmingDetails cropFarmingDetails) {
        if(Objects.isNull(cropFarmingDetails.getActualAmountOfProductHarvested())){
            validationErrorMessage = "Actual Amount Of Product Harvested is required";
        }
        return validationErrorMessage;
    }

    private String validateFarmPlantingSeasonRequest(CropFarmingDetails cropFarmingDetails) {
        if(Objects.isNull(cropFarmingDetails.getPlantingAreaInAcres())){
            validationErrorMessage = "Planting Area In Acres is required";
        }else if(Objects.isNull(cropFarmingDetails.getAmountOfExpectedProduct())){
            validationErrorMessage = "Amount Of Expected Product is required";
        }
        return validationErrorMessage;
    }
}
