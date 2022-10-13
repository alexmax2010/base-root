package com.base.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private String id;
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Integer companyCode;
}
