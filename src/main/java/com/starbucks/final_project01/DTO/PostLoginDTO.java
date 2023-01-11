package com.starbucks.final_project01.DTO;

import com.starbucks.final_project01.entity.MemberEntity;

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
    private String id;
    private String pwd;

    public static PostLoginDTO fromEntity(MemberEntity memberInfoEntity){
        return PostLoginDTO.builder()
        // .seq(memberInfoEntity.getMiSeq())
        .id(memberInfoEntity.getMiId())
        .pwd(memberInfoEntity.getMiPwd())
        .build();

    }
        
    }

