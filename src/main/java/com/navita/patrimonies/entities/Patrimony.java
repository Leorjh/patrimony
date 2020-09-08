package com.navita.patrimonies.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patrimonies")
public class Patrimony {

    @Id
    @GeneratedValue
    private Long tombo;

    @NotNull
    private String name;

    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "brandId", nullable = false,
            foreignKey = @ForeignKey(name = "FK_BRAND_ID"))
    private Brand brand;
}
