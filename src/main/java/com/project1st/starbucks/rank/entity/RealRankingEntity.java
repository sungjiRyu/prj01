package com.project1st.starbucks.rank.entity;



import org.hibernate.annotations.Immutable;

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
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_ranking")
public class RealRankingEntity {
    @Id    
    @Column(name = "mbi_name") private String mbiName;
    @Column(name = "mbi_seq") private Long mbiSeq;
    @Column(name = "img_file") private String imgFile;
    @Column(name = "cnt") private Long cnt;
}
