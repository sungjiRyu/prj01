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
@Table(name = "store_announcement")
public class AnnouncementEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sa_seq")  private Long saSeq;
    @Column(name = "sa_img_file")  private String saImgFile;
    @Column(name = "sa_uri") private String saUri;
    @Column(name = "sa_title") private String saTitle;
    @Column(name = "sa_content") private String saContent;
}