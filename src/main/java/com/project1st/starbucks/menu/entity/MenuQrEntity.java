package com.project1st.starbucks.menu.entity;

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
@Table(name = "menu_qr_image")
@Builder
public class MenuQrEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mqi_seq")               private Long mqiSeq;
    @Column(name = "mqi_image_file")        private String mqiImageFile;
    @Column(name = "mqi_uri")               private String mqiUri;
    @Column(name = "mqi_mbi_seq")           private Long mqiMbiSeq;
}
