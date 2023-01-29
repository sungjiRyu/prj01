package com.project1st.starbucks.qr;

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
@Table(name = "")
@Builder
public class QRentity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ii_seq")            private Long iiSeq;
    @Column(name = "ii_file")           private String iiFile;
    @Column(name = "ii_uri")            private String iiUri;
    @Column(name = "ii_mbi_seq")        private Long iiMbiSeq;
}
