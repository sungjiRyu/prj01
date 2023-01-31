package com.project1st.starbucks.map.entity;

import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;

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
@Builder
@Table(name = "store_gps_info")
public class StoreGPSEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sgi_seq") private Long sgiSeq;
    @Column(name = "sgi_lat") private Double sgiLat;
    @Column(name = "sgi_lon") private Double sgiLon;
    // @OneToOne @JoinColumn(name = "sgi_sbi_seq") StoreBasicInfoEntity store;
    @Column(name = "sgi_sbi_seq") private Long sgiSbiSeq;
    @Column(name = "sgi_title") private String sgiTitle;
}
