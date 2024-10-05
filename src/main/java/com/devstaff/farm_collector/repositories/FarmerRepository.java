package com.devstaff.farm_collector.repositories;

import com.devstaff.farm_collector.entities.Farmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Long> {
}
