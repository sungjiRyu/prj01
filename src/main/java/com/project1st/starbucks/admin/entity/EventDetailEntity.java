package com.project1st.starbucks.admin.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "event_detail_image")
@DynamicInsert
@Builder
public class EventDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edi_seq")    private Long ediSeq;
    @Column(name = "edi_img_file")    private String edFile;
    @Column(name = "edi_start_date")  @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate ediStartDate;
    @Column(name = "edi_end_date")    @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate ediEndDate;
    @Column(name = "edi_contents")    private String ediContents;
    @Column(name = "edi_uri")    private String ediUri;

}
