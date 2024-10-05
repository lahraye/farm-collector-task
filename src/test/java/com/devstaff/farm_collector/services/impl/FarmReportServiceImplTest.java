package com.devstaff.farm_collector.services.impl;

import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;
import com.devstaff.farm_collector.repositories.FarmReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FarmReportServiceImplTest {

    @InjectMocks
    private FarmReportServiceImpl farmReportService;

    @Mock
    private FarmReportRepository farmReportRepository;

    private FarmReportRequest farmReportRequest;
    private List<FarmReportDetails> farmReportDetailsList;

    @BeforeEach
    void setUp() {


        farmReportRequest = new FarmReportRequest(1, List.of(), null);

        FarmReportDetails farmReportDetails = new FarmReportDetails("FarmA",
                "CASHEW",
        36.9,
        80,
        20,
        2024);
        farmReportDetailsList = Arrays.asList(farmReportDetails);
    }

    @Test
    void testReportDetails_Success() {

        when(farmReportRepository.getFarmReportDetails(farmReportRequest)).thenReturn(farmReportDetailsList);

        List<FarmReportDetails> response = farmReportService.reportDetails(farmReportRequest);

        verify(farmReportRepository, times(1)).getFarmReportDetails(farmReportRequest);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("FarmA", response.get(0).farmName());
        assertEquals(2024, response.get(0).year());
    }

    @Test
    void testReportDetails_EmptyResult() {

        when(farmReportRepository.getFarmReportDetails(farmReportRequest)).thenReturn(List.of());

        List<FarmReportDetails> response = farmReportService.reportDetails(farmReportRequest);

        verify(farmReportRepository, times(1)).getFarmReportDetails(farmReportRequest);

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

}