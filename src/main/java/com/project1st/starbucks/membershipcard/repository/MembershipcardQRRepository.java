package com.project1st.starbucks.membershipcard.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.membershipcard.entity.MembershipCardQREntity;

@Repository
public interface MembershipcardQRRepository extends JpaRepository<MembershipCardQREntity, Long> {
    public MembershipCardQREntity findTopByCardqrUriOrderByCardqrSeqDesc(String uri);
    MembershipCardQREntity findByCardqrMiSeq(Long miSeq);
}
