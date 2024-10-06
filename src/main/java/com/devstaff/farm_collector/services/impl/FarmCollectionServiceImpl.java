package com.devstaff.farm_collector.services.impl;

import com.devstaff.farm_collector.entities.Crop;
import com.devstaff.farm_collector.entities.FarmField;
import com.devstaff.farm_collector.entities.FieldOperation;
import com.devstaff.farm_collector.exceptions.CustomException;
import com.devstaff.farm_collector.exceptions.NotFoundException;
import com.devstaff.farm_collector.models.CropFarmingDetails;
import com.devstaff.farm_collector.models.FarmFieldDetail;
import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;
import com.devstaff.farm_collector.repositories.CropRepository;
import com.devstaff.farm_collector.repositories.FarmFieldRepository;
import com.devstaff.farm_collector.repositories.FarmRepository;
import com.devstaff.farm_collector.repositories.FieldOperationRepository;
import com.devstaff.farm_collector.services.FarmCollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
public class FarmCollectionServiceImpl implements FarmCollectionService  {

    private final FarmRepository farmRepository;
    private final FarmFieldRepository farmFieldRepository;
    private final FieldOperationRepository fieldOperationRepository;
    private final CropRepository cropRepository;

    @Override
    @Transactional
    public FarmDetailResponse processFarmInfo(FarmDetailRequest request) {
        Long farmFieldId = request.getFarmFieldId();
        int year = request.getYear();
        var farmDetail = request.getFarmFieldDetails();

        var farmField = farmFieldRepository.findById(farmFieldId).orElseThrow((() -> new NotFoundException("Farm field not found")));

        List<Long> cropIds = setCropDetails(farmDetail);
        validateRequestIsUnique(cropIds, farmFieldId, year);


        //group by crop
        var farmDetailsByCrop =  farmDetail.stream().collect(
                Collectors.groupingBy(FarmFieldDetail::getCrop,
                Collectors.flatMapping(x -> x.getCropFarmingDetails().stream(), Collectors.toList())));

        List<FieldOperation> fieldOperationRecordsToPersist = buildFarmFieldRecords(farmField, year, farmDetailsByCrop);

        fieldOperationRepository.saveAll(fieldOperationRecordsToPersist);

        return FarmDetailResponse.builder().farmId(farmField.getFarm().getId()).farmFieldId(farmField.getId()).build();
    }

    private void validateRequestIsUnique(List<Long> cropIds, Long farmFieldId, int year) {
        boolean recordExists = fieldOperationRepository.existsByFarmFieldIdAndCropIdInAndYear(farmFieldId, cropIds, year);

        if(recordExists){
            throw new CustomException("A record exists already for 1 or more crops in your request");
        }
    }

    private List<Long> setCropDetails(Set<FarmFieldDetail> farmDetail) {
        return farmDetail.stream().map(fd -> {
            Crop crop = cropRepository.findById(fd.getCropId()).orElseThrow(() -> new NotFoundException(String.format("Crop with ID %s not found", fd.getCropId())));
            fd.setCrop(crop);
            return crop;
        }).map(Crop::getId).collect(Collectors.toList());

    }

    private List<FieldOperation> buildFarmFieldRecords(FarmField farmField, int year, Map<Crop, List<CropFarmingDetails>> cropFarmingDetails) {
        List<FieldOperation> farmFields = new ArrayList<>();

        cropFarmingDetails.forEach((crop, farmDetails) -> {
            FieldOperation farmOperation = new FieldOperation();
            farmOperation.setCrop(crop);
            farmOperation.setFarmField(farmField);
            farmOperation.setYear(year);

            farmDetails.forEach(farmDetailItem -> {
                switch (farmDetailItem.getSeason()){
                    case SPRING -> {
                        farmOperation.setAmountOfProductExpected(farmDetailItem.getAmountOfExpectedProduct());
                        farmOperation.setPlantingArea(farmDetailItem.getPlantingAreaInAcres());
                    }
                    case FALL -> farmOperation.setActualAmountOfProductHarvested(farmDetailItem.getActualAmountOfProductHarvested());
                }
            });
            farmFields.add(farmOperation);
        });

        return farmFields;
    }
}
