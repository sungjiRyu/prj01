package com.project1st.starbucks.store.entity;

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
@Table(name = "store_basic_info")
@DynamicInsert
public class StoreBasicInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sbi_seq")                       private Long sbiSeq;
    @Column(name = "sbi_branch_name")               private String sbiBranchName;        
    @Column(name = "sbi_address_basic")             private String sbiAddressBasic;        
    @Column(name = "sbi_address_detail")            private String sbiAddressDetail;            
    @Column(name = "sbi_open_time")                 @ColumnDefault("08:00")     private String sbiOpenTime;    
    @Column(name = "sbi_close_time")                @ColumnDefault("22:00")     private String sbiCloseTime;        
    @Column(name = "sbi_close_day")                 private String sbiCloseDay;    
    @Column(name = "sbi_min_order")                 @ColumnDefault("5000")      private Integer sbiMinOrder;    
    @Column(name = "sbi_ceo_name")                  private String sbiCeoName;    
    @Column(name = "sbi_business_address")          private String sbiBuisnessAddress;            
    @Column(name = "sbi_phone")                     private String sbiPhone;
    @Column(name = "sbi_min_delivery_time")         @ColumnDefault("15")        private String sbiMinDeliveryTime;            
    @Column(name = "sbi_max_delivery_time")         @ColumnDefault("100")       private String sbiMaxDeliveryTime;            

}