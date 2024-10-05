package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
