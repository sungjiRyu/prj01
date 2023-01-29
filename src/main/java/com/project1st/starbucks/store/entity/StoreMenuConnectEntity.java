package com.project1st.starbucks.store.entity;



import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_menu_connect")
@Builder
@DynamicInsert
public class StoreMenuConnectEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smc_seq")               private Long StoreMenuNo;
    @Column(name = "smc_menu_stock")        @ColumnDefault("100")   private Integer StoreMenuStock;        
    @OneToOne @JoinColumn(name = "smc_sbi_seq") StoreBasicInfoEntity store; // 가게
    // @Column(name = "smc_sbi_seq")           private Integer smcSbiSeq;    // 가게
    @OneToOne @JoinColumn(name = "smc_mbi_seq") MenuBasicInfoEntity menu;   //메뉴
    // @Column(name = "smc_mbi_seq")           private Integer smcMbiSeq;    // 메뉴
}