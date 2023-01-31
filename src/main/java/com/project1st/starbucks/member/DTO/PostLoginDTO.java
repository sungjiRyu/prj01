package com.project1st.starbucks.member.DTO;

import com.project1st.starbucks.admin.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostLoginDTO {
    // private Long seq;
    private String miId;
    private String miPwd;

    public static PostLoginDTO fromEntity(MemberEntity memberInfoEntity){
        return PostLoginDTO.builder()
        // .seq(memberInfoEntity.getMiSeq())
        .miId(memberInfoEntity.getMiId())
        .miPwd(memberInfoEntity.getMiPwd())
        .build();
    }
    }

