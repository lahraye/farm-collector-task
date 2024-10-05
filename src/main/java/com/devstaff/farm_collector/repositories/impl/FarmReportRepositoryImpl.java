package com.devstaff.farm_collector.repositories.impl;

import com.devstaff.farm_collector.models.FarmReportDetails;
import com.devstaff.farm_collector.models.FarmReportRequest;
import com.devstaff.farm_collector.repositories.FarmReportRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.devstaff.farm_collector.repositories.jooq.Tables.*;

@Repository
@RequiredArgsConstructor
public class FarmReportRepositoryImpl implements FarmReportRepository {

    private final DSLContext dslContext;

    @Override
    public List<FarmReportDetails> getFarmReportDetails(FarmReportRequest request) {


        return buildQuery(request).fetchInto(FarmReportDetails.class);
    }

    private SelectConditionStep<?> buildQuery(FarmReportRequest request) {
        var fo = FARM_OPERATIONS;
        var f = FARMS;
        var ff = FARM_FIELDS;
        var c = CROPS;
        var farmId = request.farmId();
        var year = request.year();
        var cropIds = request.crops();

        var query = dslContext.select(f.NAME.as("fieldName"),
                                                                      c.NAME.as("cropName"),
                                                                      fo.PLANTING_AREA_SIZE,
                                                                    fo.AMOUNT_EXPECTED,
                                                                    fo.AMOUNT_HARVESTED,
                                                                    fo.YEAR)
                                                            .from(FARM_OPERATIONS)
                                                            .innerJoin(c).on(fo.CROP_ID.eq(c.ID))
                                                            .innerJoin(ff).on(fo.FIELD_ID.eq(ff.ID))
                                                            .innerJoin(f).on(ff.FARM_ID.eq(f.ID))
                                                            .where(DSL.noCondition());

        if(farmId != null){
            query = query.and(f.ID.eq(farmId));
        }

        if(year != null){
            query = query.and(fo.YEAR.eq(year));
        }

        if(cropIds !=null && !cropIds.isEmpty()){
            query = query.and(fo.CROP_ID.in(cropIds));
        }
        return query;
    }
}
