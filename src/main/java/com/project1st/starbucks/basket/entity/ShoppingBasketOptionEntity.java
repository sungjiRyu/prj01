package com.project1st.starbucks.basket.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "shopping_basket_option")
// @JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class ShoppingBasketOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sbo_seq") private Long sboSeq;
    @Column(name = "sbo_number") private Long sboNumber;
    @OneToOne
    @JoinColumn(name = "sbo_moi_seq") MenuOptionInfoEntity menuOption;
    // @Column(name = "sbo_moi_seq") private Long sboMoiSeq;
    @ManyToOne
    @JoinColumn(name = "sbo_sb_seq") private ShoppingBasketEntity shoppingBasket;
    @Column(name = "sbo_option_order_number") private Integer sboOptionOrderNumber;
    @Column(name = "sbo_option_price") private Long sbOptionPrice;

    // @Column(name = "sbo_sb_seq") private Long sboSbSeq;
}
