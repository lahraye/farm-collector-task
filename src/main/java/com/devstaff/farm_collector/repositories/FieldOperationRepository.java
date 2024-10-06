package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.entities.FieldOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldOperationRepository extends JpaRepository<FieldOperation, Long> {

    boolean existsByFarmFieldIdAndCropIdInAndYear(Long farmFieldId, List<Long> cropIds, int year);
}
