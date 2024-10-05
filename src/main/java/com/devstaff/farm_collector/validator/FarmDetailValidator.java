package com.devstaff.farm_collector.validator;

import com.devstaff.farm_collector.models.FarmDetail;
import org.codehaus.plexus.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FarmDetailValidator implements ConstraintValidator<ValidFarmDetail, FarmDetail> {

    private String validationErrorMessage;
    @Override
    public void initialize(ValidFarmDetail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(FarmDetail farmDetail, ConstraintValidatorContext context) {
        var season = farmDetail.getSeason();
        
        var validationErrorMessage = switch(season){
            case PLANTED -> validateFarmPlantingSeasonRequest(farmDetail);
            case HARVESTED -> validateFarmHarvestingSeasonRequest(farmDetail);
        };

        if(StringUtils.isBlank(validationErrorMessage)){
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(validationErrorMessage).addConstraintViolation();
        return false;
    }

    private String validateFarmHarvestingSeasonRequest(FarmDetail farmDetail) {
        if(Objects.isNull(farmDetail.getActualAmountOfProductHarvested())){
            validationErrorMessage = "Actual Amount Of Product Harvested is required";
        }
        return validationErrorMessage;
    }

    private String validateFarmPlantingSeasonRequest(FarmDetail farmDetail) {
        if(Objects.isNull(farmDetail.getPlantingAreaInAcres())){
            validationErrorMessage = "Planting Area In Acres is required";
        }else if(Objects.isNull(farmDetail.getAmountOfExpectedProduct())){
            validationErrorMessage = "Amount Of Expected Product is required";
        }
        return validationErrorMessage;
    }
}
