package com.project1st.starbucks.member.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAuthNumByEmailDTO {
    String name;
    String email;
    public Object getId() {
        return null;
    }
}

