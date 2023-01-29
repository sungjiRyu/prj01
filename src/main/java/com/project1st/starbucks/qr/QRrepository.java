package com.project1st.starbucks.qr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRrepository extends JpaRepository<QRentity, Long> {
    
}
