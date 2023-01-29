package com.project1st.starbucks.membershipcard.entity;

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
@Table(name = "membership_card_qr_image")
@Builder
public class MembershipCardQREntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardqr_seq")            private Long cardqrSeq;
    @Column(name = "cardqr_file")           private String cardqrFile;
    @Column(name = "cardqr_uri")            private String cardqrUri;
    @Column(name = "cardqr_mi_seq")         private Long cardqrMiSeq;
}
