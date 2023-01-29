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
@Table(name = "membership_card_image")
public class MembershipImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardimage_seq")                private Long ciSeq;
    @Column(name = "cardimage_name")                 private String ciName;
    @Column(name = "cardimage_file")                private String ciImgFile;
    @Column(name = "cardimage_uri")               private String ciUri;
}
