package com.devstaff.farm_collector.services.impl;

import com.devstaff.farm_collector.exceptions.CustomException;
import com.devstaff.farm_collector.exceptions.NotFoundException;
import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;
import com.devstaff.farm_collector.repositories.FarmRepository;
import com.devstaff.farm_collector.services.FarmCollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class FarmCollectionServiceImpl implements FarmCollectionService  {

    private final FarmRepository farmRepository;
    @Override
    public FarmDetailResponse processFarmInfo(FarmDetailRequest request) {
        //validate that farm exists
        var farm = farmRepository.findById(request.getFarmId()).orElseThrow((() -> new NotFoundException("Farm not found")));
        if(farm != null)
        //validate crop exists
        return null;
    }
}
