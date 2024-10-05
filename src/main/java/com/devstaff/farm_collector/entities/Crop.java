package com.devstaff.farm_collector.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "crops")
public class Crop extends BaseEntity{
    private String name;
}
