package com.devstaff.farm_collector.services.impl;

import com.devstaff.farm_collector.constants.Season;
import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;
import com.devstaff.farm_collector.repositories.FarmReportRepository;
import com.devstaff.farm_collector.repositories.FarmRepository;
import com.devstaff.farm_collector.services.FarmReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class FarmReportServiceImpl implements FarmReportService {

    private final FarmReportRepository farmReportRepository;

    @Override
    public List<FarmReportDetails> reportDetails(FarmReportRequest request) {
        return farmReportRepository.getFarmReportDetails(request);
    }
}
