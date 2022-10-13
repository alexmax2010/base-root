package com.base.vo;

import lombok.*;

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
}
