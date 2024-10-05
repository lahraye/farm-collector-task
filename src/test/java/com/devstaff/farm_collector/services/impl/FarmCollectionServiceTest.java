package com.devstaff.farm_collector.services.impl;


import com.devstaff.farm_collector.constants.Season;
import com.devstaff.farm_collector.entities.Crop;
import com.devstaff.farm_collector.entities.Farm;
import com.devstaff.farm_collector.entities.FarmField;
import com.devstaff.farm_collector.exceptions.NotFoundException;
import com.devstaff.farm_collector.models.CropFarmingDetails;
import com.devstaff.farm_collector.models.FarmDetailRequest;
import com.devstaff.farm_collector.models.FarmDetailResponse;
import com.devstaff.farm_collector.models.FarmFieldDetail;
import com.devstaff.farm_collector.repositories.CropRepository;
import com.devstaff.farm_collector.repositories.FarmFieldRepository;
import com.devstaff.farm_collector.repositories.FarmRepository;
import com.devstaff.farm_collector.repositories.FieldOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FarmCollectionServiceTest {

    @InjectMocks
    private FarmCollectionServiceImpl farmCollectionService;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmFieldRepository farmFieldRepository;

    @Mock
    private FieldOperationRepository fieldOperationRepository;

    @Mock
    private CropRepository cropRepository;

    private FarmDetailRequest farmDetailRequest;
    private FarmField farmField;
    private Crop crop;
    private FarmFieldDetail farmFieldDetail;
    private Set<FarmFieldDetail> farmFieldDetails;
    private CropFarmingDetails cropFarmingDetails;



    @BeforeEach
    void setUp() {

        // Mock farm field data
        farmField = new FarmField();
        Farm farm = new Farm();
        farm.setId(1L);
        farmField.setFarm(farm);
        farmField.setId(1L);

        crop = new Crop();
        crop.setId(1L);
        crop.setName("Wheat");

        cropFarmingDetails = new CropFarmingDetails();
        cropFarmingDetails.setSeason(Season.SPRING);
        cropFarmingDetails.setAmountOfExpectedProduct(100.0);
        cropFarmingDetails.setPlantingAreaInAcres(50.0);

        farmFieldDetail = new FarmFieldDetail();
        farmFieldDetail.setCropId(1L);
        farmFieldDetail.setCropFarmingDetails(List.of(cropFarmingDetails));

        farmFieldDetails = Set.of(farmFieldDetail);

        farmDetailRequest = new FarmDetailRequest();
        farmDetailRequest.setFarmFieldId(1L);
        farmDetailRequest.setFarmFieldDetails(farmFieldDetails);
        farmDetailRequest.setYear("2024");
    }


    @Test
    void testProcessFarmInfo_Success() {
        // Mock repository calls
        when(farmFieldRepository.findById(1L)).thenReturn(Optional.of(farmField));
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));

        // Call the service method
        FarmDetailResponse response = farmCollectionService.processFarmInfo(farmDetailRequest);

        // Verify the interactions with repositories
        verify(farmFieldRepository, times(1)).findById(1L);
        verify(cropRepository, times(1)).findById(1L);
        verify(fieldOperationRepository, times(1)).saveAll(anyList());

        // Assert the response
        assertNotNull(response);
        assertEquals(1L, response.getFarmId());
        assertEquals(1L, response.getFarmFieldId());
    }

    @Test
    void testProcessFarmInfo_FarmFieldNotFound() {
        // Mock repository call to return empty
        when(farmFieldRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        assertThrows(NotFoundException.class, () -> farmCollectionService.processFarmInfo(farmDetailRequest));

        // Verify the interaction
        verify(farmFieldRepository, times(1)).findById(1L);
        verify(cropRepository, never()).findById(anyLong());
        verify(fieldOperationRepository, never()).saveAll(anyList());
    }

    @Test
    void testProcessFarmInfo_CropNotFound() {
        // Mock farm field found, but crop not found
        when(farmFieldRepository.findById(1L)).thenReturn(Optional.of(farmField));
        when(cropRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        assertThrows(NotFoundException.class, () -> farmCollectionService.processFarmInfo(farmDetailRequest));

        // Verify the interaction
        verify(farmFieldRepository, times(1)).findById(1L);
        verify(cropRepository, times(1)).findById(1L);
        verify(fieldOperationRepository, never()).saveAll(anyList());
    }
}