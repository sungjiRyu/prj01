package com.project1st.starbucks.menu.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "menu_basic_info")
@DynamicInsert
public class MenuBasicInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mbi_seq")           private Long mbiSeq;
    @Column(name = "mbi_name")          private String mbiName;
    @Column(name = "mbi_cost")          private Integer mbiCost;    
    @Column(name = "mbi_status")        @ColumnDefault("1")     private Integer mbiStatus;
    @Column(name = "mbi_explain")       private String mbiExplain;
    @Column(name = "mbi_pc_seq")        private Long mbiPcSeq;
}
