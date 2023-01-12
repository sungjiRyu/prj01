package com.project1st.starbucks.conpon.entity;

import java.util.Date;

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
@Table(name = "coupon_info")
@DynamicInsert
public class CouponInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_seq")            private Long ciSeq;
    @Column(name = "ci_discount")       private Integer ciDiscount;
    @Column(name = "ci_reg_dt")         @ColumnDefault("current_timestamp")     private Date ciRegDt;
    @Column(name = "ci_ex_dt")          private Date ciExDt;
    @Column(name = "ci_name")           private String ciName;
    @Column(name = "ci_explain")        private String ciExplain;
    @Column(name = "ci_stock")          private Integer ciStock;
    @Column(name = "ci_code")           private String ciCode;
}