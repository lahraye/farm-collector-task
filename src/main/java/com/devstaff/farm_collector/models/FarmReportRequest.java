package com.devstaff.farm_collector.models;

import java.util.List;

public record FarmReportRequest(Integer farmId,
                                List<Integer> crops,
                                Integer year) {
}
