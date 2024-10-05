package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.entities.FieldOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOperationRepository extends JpaRepository<FieldOperation, Long> {
}
