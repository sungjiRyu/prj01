package com.starbucks.final_project01.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nullable;
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
@DynamicInsert
@Table(name = "member_info")
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mi_seq")                private Long miSeq;
    @Column(name = "mi_id")                 private String miId;
    @Column(name = "mi_pwd")                private String miPwd;
    @Column(name = "mi_name")               private String miName;
    @Column(name = "mi_nickname")           private String miNickname;
    @Column(name = "mi_birth")              private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate miBirth;
    @Column(name = "mi_gen")                @Nullable   @ColumnDefault("0")    private Integer miGen;
    @Column(name = "mi_phonenum")           private String miPhoneNum;
    @Column(name = "mi_status")             @ColumnDefault("1")     private Integer miStatus;
    @Column(name = "mi_reg_date")           @ColumnDefault ("current_timestamp")     private LocalDate miRegDate;
    @Column(name = "mi_group")              private Integer miGroup;
    @Column(name = "mi_business_num")       @Nullable   private String miBusinessNum;
    @Column(name="mi_address")              private String miAddress;
    @Column(name = "mi_detail_address")     private String miDetailAddress;
    @Column(name = "mi_last_login")         private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate miLastLogin;
    @Column(name = "mi_sbi_seq")            private Long miSbiSeq;

}
