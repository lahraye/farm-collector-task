package com.devstaff.farm_collector.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "farms")
public class Farm extends BaseEntity{
    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "farmer_id", nullable = false)
    private Farmer farmer;
}
