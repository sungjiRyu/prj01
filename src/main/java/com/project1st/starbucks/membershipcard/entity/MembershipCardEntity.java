package com.project1st.starbucks.membershipcard.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.annotation.Nullable;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membership_card")
@DynamicInsert
public class MembershipCardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_seq")          private Long cardSeq;
    @Column(name = "card_name")         @ColumnDefault("수타벅스 멤버십카드")     private String cardName;
    @Column(name = "card_money")        @Nullable   @ColumnDefault("0")    private Integer cardMoney;
    @Column(name = "card_mi_seq")      private Long cardMiSeq;
    @Column(name = "card_image")        @Nullable   @ColumnDefault("1")    private Long cardImage;
}
