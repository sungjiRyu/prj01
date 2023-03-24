package com.project1st.starbucks.admin.entity;

import org.hibernate.annotations.DynamicInsert;
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
@Table(name = "store_image")
public class StoreImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq")    private Long siSeq;
    @Column(name = "si_img_file")   private String siImgFile;
    @Column(name = "si_number")    private Long siNumber;
    @Column(name = "si_uri")    private String siUri;
}