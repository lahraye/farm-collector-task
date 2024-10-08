package com.devstaff.farm_collector.services;

import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;


public interface FarmCollectionService {
    FarmDetailResponse processFarmInfo(FarmDetailRequest request) throws Throwable;
}
