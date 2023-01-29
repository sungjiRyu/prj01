package com.project1st.starbucks.membershipcard.entity;

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
@Table(name = "membership_card_image")
public class MembershipCardImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardimage_seq")         private Long cardimageSeq;
    @Column(name = "cardimage_name")        private String cardimageName;
    @Column(name = "cardimage_file")        private String cardimageFile;
    @Column(name = "cardimage_uri")         private String cardimageUri;
}
