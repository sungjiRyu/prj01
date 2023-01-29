package com.project1st.starbucks.admin.entity;

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
@DynamicInsert
@Builder
@Table(name = "menu_image_info")
public class MenuImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mii_seq")           private Long miiSeq;
    @OneToOne @JoinColumn(name = "mii_number")        private MenuBasicInfoEntity miiNumber;
    @Column(name = "mii_img_file")      private String miiImgFile;
    @Column(name = "mii_uri")           private String miiUri;
}
