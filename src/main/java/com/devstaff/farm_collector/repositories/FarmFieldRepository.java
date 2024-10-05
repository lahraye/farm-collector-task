package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.entities.FarmField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmFieldRepository extends JpaRepository<FarmField, Long> {
}
