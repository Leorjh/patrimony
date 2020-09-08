package com.navita.patrimonies.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands",
        uniqueConstraints = {@UniqueConstraint(name = "BRAND_UNIQUE_NAME", columnNames = {"name"})})
public class Brand {

    @Id
    @GeneratedValue
    private Long brandId;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "brand", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Patrimony> patrimonies;

}
