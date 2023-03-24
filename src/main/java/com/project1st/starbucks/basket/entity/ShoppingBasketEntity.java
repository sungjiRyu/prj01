package com.project1st.starbucks.basket.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Data
@Getter
@Setter
// @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "shopping_basket")
// @JsonIdentityInfo(generator = IntSequenceGenerator.class, property = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class ShoppingBasketEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sb_seq") private Long sbSeq;
    @Column(name = "sb_status") private Long sbStatus;
    @Column(name = "sb_order_date") private Date sborderDate;
    @Column(name = "sb_request") private String sbRequest;
    @Column(name = "sb_receive") private String sbReceive;
    @Column(name = "sb_payment") private Long sbPayment;
    @Column(name = "sb_number") private Long sbNumber;
    @Column(name = "sb_menu_image_name") private String menuImageName;
    @Column(name = "sb_menu_image_uri") private String menuImageUri;
    @OneToOne
    @JoinColumn(name = "sb_smc_seq") StoreMenuConnectEntity storeMenuConnect;    
    // @Column(name = "sb_smc_seq") private Long sbSmcSeq;
    @Column(name = "sb_mi_seq") private Long sbMiSeq;
    @Column(name = "sb_order_number") private Integer sbOrderNumber;
    @Column(name = "sb_basket_price") private Long sbBasketPrice;
    @Column(name = "sb_menu_total_price") private Long optionIncludePrice;

    @OneToMany(mappedBy = "shoppingBasket", cascade = CascadeType.ALL)
    private List<ShoppingBasketOptionEntity> shoppingBasketOption;
}

