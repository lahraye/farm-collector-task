package com.devstaff.farm_collector.controllers;

import com.devstaff.farm_collector.constants.AppConstants;
import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;
import com.devstaff.farm_collector.services.FarmCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/collection")
@RequiredArgsConstructor
public class FarmCollectionController {

    private final FarmCollectionService farmCollectionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FarmDetailResponse collectFarmDetails(@Valid @RequestBody FarmDetailRequest request){
        return farmCollectionService.processFarmInfo(request);
    }

}
