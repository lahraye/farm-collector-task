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
@Table(name = "farmers")
public class Farmer extends BaseEntity{
    private String name;
}
