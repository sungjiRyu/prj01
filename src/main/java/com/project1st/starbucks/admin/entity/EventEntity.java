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
@Table(name = "event_image")
@DynamicInsert
@Builder
public class EventEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_seq")            private Long evSeq;
    @Column(name = "ev_img_file")       private String evFile;
    @Column(name = "ev_uri")            private String evUri;
    @Column(name = "ev_start_date")     @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate evStartDate; 
    @Column(name = "ev_end_date")       @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate evEndDate;
    @Column(name = "ev_content")        private String evContent;
}
