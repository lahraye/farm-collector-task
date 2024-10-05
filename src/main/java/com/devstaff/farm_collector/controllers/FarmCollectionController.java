package com.devstaff.farm_collector.controllers;

import com.devstaff.farm_collector.constants.AppConstants;
import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;
import com.devstaff.farm_collector.services.FarmCollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/farm-collections")
@RequiredArgsConstructor
public class FarmCollectionController {

    private final FarmCollectionService farmCollectionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FarmDetailResponse collectFarmDetails(@Valid @RequestBody FarmDetailRequest request) throws Throwable {
        return farmCollectionService.processFarmInfo(request);
    }
}
