package com.starbucks.final_project01.entity;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


import io.micrometer.common.lang.Nullable;
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
@Table(name = "member_info")
@DynamicInsert
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq")                                                      private Long miSeq;
    @Column(name="mi_id")                                                       private String miId;
    @Column(name="mi_pwd")                                                      private String miPwd;
    @Column(name="mi_name")                                                     private String miName;
    @Column(name="mi_nickname")                                                 private String miNickname;
    @Column(name="mi_birth")                                                    private Date miBirth;
    @Column(name="mi_gen")           @Nullable  @ColumnDefault("0")             private Integer miGen;
    @Column(name="mi_status")        @Nullable  @ColumnDefault("1")             private Integer miStatus;
    @Column(name="mi_phonenum")                                                 private String miPhoneNum;
    @Column(name="mi_reg_date")      @ColumnDefault ("curdate")                 private Date miRegDate;
    @Column(name="mi_group")                                                    private Integer miGroup;
    @Column(name="mi_business_num")  @Nullable                                  private String miBusinessNum; 
    @Column(name="mi_address")                                                  private String miAddress;
    @Column(name="mi_last_login")    @ColumnDefault ("current_timestamp")       private Date miLastLogin; 
    @Column(name="mi_detail_address")                                           private String miDetailAddress;
}
