package com.devstaff.farm_collector.controllers;

import com.devstaff.farm_collector.constants.AppConstants;
import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;
import com.devstaff.farm_collector.services.FarmReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/reports")
@RequiredArgsConstructor
public class FarmReportController {

    private final FarmReportService farmReportService;


    @GetMapping
    public List<FarmReportDetails> getFarmReportDetails(@RequestParam(required = false) Integer farmId,
                                                        @RequestParam(required = false) List<Integer> crops,
                                                        @RequestParam(required = false) Integer year){
        FarmReportRequest farmReportRequest = new FarmReportRequest(farmId, crops, year);
        return farmReportService.reportDetails(farmReportRequest);
    }

}
