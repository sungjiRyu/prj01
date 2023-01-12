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
@Table(name = "counpon_member_info")
@DynamicInsert
public class CouponMemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmi_seq")           private Long cmiSeq;
    @Column(name = "cmi_status")        @ColumnDefault("1")     private Integer cmiStatus;
    @Column(name = "cmi_use_date")      private Date cmiUseDate;
    @Column(name = "cmi_ci_seq")        private Long cmiCiSeq;
    @Column(name = "cmi_mi_seq")        private Long cmiMiSeq;
}
