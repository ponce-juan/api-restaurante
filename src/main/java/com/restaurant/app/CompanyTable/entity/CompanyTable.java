package com.restaurant.app.CompanyTable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.app.Company.entity.Company;
import com.restaurant.app.CompanyTable.model.TableLocationEnum;
import com.restaurant.app.CompanyTable.model.TableStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name="company_tables")
public class CompanyTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    @Min(1)
    private int number;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    @Min(1)
    @Max(10)
    private int seats;

    @Enumerated(EnumType.STRING)
    private TableLocationEnum location = TableLocationEnum.INDOOR;

    @Enumerated(EnumType.STRING)
    private TableStatusEnum status = TableStatusEnum.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;

}
