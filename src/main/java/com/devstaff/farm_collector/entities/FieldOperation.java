package com.devstaff.farm_collector.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "farm_operations")
public class FieldOperation extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "crop_id", referencedColumnName = "id", nullable = false)
    private Crop crop;

    @Column(name = "planting_area_size")
    private Double plantingArea;

    @Column(name = "amount_expected")
    private Double amountOfProductExpected;

    @Column(name = "amount_harvested")
    private Double actualAmountOfProductHarvested;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "field_id", nullable = false)
    private FarmField farmField;

    private int year;
}
