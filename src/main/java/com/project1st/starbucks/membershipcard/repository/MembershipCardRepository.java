package com.project1st.starbucks.membershipcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;

@Repository
public interface MembershipCardRepository extends JpaRepository<MembershipCardEntity, Long> {
    MembershipCardEntity findByCardMiSeq(Long cardMiSeq);
}
