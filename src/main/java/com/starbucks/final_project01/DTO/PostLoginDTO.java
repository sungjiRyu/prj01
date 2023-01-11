package com.starbucks.final_project01.DTO;

<<<<<<< HEAD
import com.starbucks.final_project01.entity.MemberEntity;
=======
import com.starbucks.final_project01.entity.MemberInfoEntity;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed

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

<<<<<<< HEAD
    public static PostLoginDTO fromEntity(MemberEntity memberInfoEntity){
=======
    public static PostLoginDTO fromEntity(MemberInfoEntity memberInfoEntity){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
        return PostLoginDTO.builder()
        // .seq(memberInfoEntity.getMiSeq())
        .id(memberInfoEntity.getMiId())
        .pwd(memberInfoEntity.getMiPwd())
        .build();

    }
        
    }

