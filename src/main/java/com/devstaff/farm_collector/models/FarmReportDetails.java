package com.devstaff.farm_collector.models;

public record FarmReportDetails(String farmName,
                                String cropName,
                                double plantingAreaSize,
                                long amountExpected,
                                long amountHarvested,
                                int year) {

}
