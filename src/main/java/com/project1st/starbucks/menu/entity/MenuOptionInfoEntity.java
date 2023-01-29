package com.project1st.starbucks.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_option_info")
public class MenuOptionInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moi_seq")           private Long moiSeq;
    @Column(name = "moi_name")          private String moiName;
    @Column(name = "moi_cost")          private Integer moiCost;
    @Column(name = "moi_moc_seq")       private Long moiMocSeq;
}
