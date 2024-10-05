package com.devstaff.farm_collector.services;

import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;

import java.util.List;

public interface FarmReportService {

    List<FarmReportDetails> reportDetails(FarmReportRequest request);
}
