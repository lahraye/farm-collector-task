package com.devstaff.farm_collector.controllers;

import com.devstaff.farm_collector.constants.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/report")
@RequiredArgsConstructor
public class FarmReportController {
}
