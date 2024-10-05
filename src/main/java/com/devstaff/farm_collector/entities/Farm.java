package com.devstaff.farm_collector.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "farm")
public class Farm extends BaseEntity{
    private String name;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "farmer_id", nullable = false)
    private Farmer farmer;
}
