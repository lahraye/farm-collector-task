package com.devstaff.farm_collector.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "farm_fields")
public class FarmField extends BaseEntity{
    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "farm_id", nullable = false)
    private Farm farm;
}
