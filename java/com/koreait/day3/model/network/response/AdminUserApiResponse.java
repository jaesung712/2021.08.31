package com.koreait.day3.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiResponse {
    private long id;
    private String userid;
    private String userpw;
    private String name;
    private String status;
    private LocalDateTime lastLoginAt;
    private LocalDateTime regDate;
    private String createBy;
}