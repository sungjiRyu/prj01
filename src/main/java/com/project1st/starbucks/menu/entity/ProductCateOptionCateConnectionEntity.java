package com.project1st.starbucks.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productcate_optioncate_connection")
public class ProductCateOptionCateConnectionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_seq")               private Long pocSeq;
    @OneToOne @JoinColumn(name = "poc_pc_seq")            ProductCategoryEntity productCategory;
    @OneToOne @JoinColumn(name = "poc_moc_seq")           MenuOptionCategoryEntity menuOpionCategory;
    // @Column(name = "poc_pc_seq")            private Long pocPcSeq;
    // @Column(name = "poc_moc_seq")           private Long pocMocSeq;
}
