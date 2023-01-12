package com.project1st.starbucks.admin.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Builder
@Table(name = "coupon_info")
public class CouponEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_seq")    private Integer ciSeq;
    @Column(name = "ci_discount")   private Long ciDiscount;
    @Column(name = "ci_reg_dt") @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate ciRegDt;
    @Column(name = "ci_ex_dt")  @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate ciExDt;
    @Column(name = "ci_name")   private String ciName;
    @Column(name = "ci_explain")    private String ciExplain;
    @Column(name = "ci_stock")  private Long ciStock;
    @Column(name = "ci_code")   private String ciCode;
    
}
