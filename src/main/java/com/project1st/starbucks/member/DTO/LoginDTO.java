package com.project1st.starbucks.member.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private Long no;
    private String id;
    private String pwd;

    // public LoginDTO(MemberInfoDTO memberInfoEntity) {
    //     this.no = memberInfoEntity.getMiSeq();
    //     this.id = memberInfoEntity.getMiId();
    //     this.pwd = memberInfoEntity.getMiPwd();
    // }
}

