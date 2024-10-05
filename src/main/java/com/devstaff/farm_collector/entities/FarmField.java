package com.devstaff.farm_collector.entities;


import com.devstaff.farm_collector.constants.Season;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "farm_field")
public class FarmField extends BaseEntity{
    @Column(name = "field_name")
    private String fieldName;

    @Enumerated(EnumType.STRING)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "crop_id", referencedColumnName = "id", nullable = false)
    private Crops crop;

    private long amountOfProductExpectedInTons;

    private long actualAmountOfProductHarvestedInTons;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "farm_id", nullable = false)
    private Farm farm;
}
