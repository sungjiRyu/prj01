package com.project1st.starbucks.member.DTO;

import com.project1st.starbucks.admin.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostFindPwdDTO {
    private String name;
    private String Id;
}

