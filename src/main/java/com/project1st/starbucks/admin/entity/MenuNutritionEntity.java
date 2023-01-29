package com.project1st.starbucks.admin.entity;

import org.hibernate.annotations.DynamicInsert;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "menu_nutrition")
public class MenuNutritionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mn_seq")      private Long mnSeq;
    @Column(name = "mn_img_file")      private String mnImgFile;
    @Column(name = "mn_uri")      private String mnUri;
    @Column(name = "mn_mbi_seq")      private Long mnMbiSeq;
}
