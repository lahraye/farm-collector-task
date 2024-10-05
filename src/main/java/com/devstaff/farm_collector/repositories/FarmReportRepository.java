package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;

import java.util.List;

public interface FarmReportRepository {

    List<FarmReportDetails> getFarmReportDetails(FarmReportRequest request);
}
