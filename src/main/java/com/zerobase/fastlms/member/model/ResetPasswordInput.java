package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResetPasswordInput {
    private String userId; // 비밀번호 찾고 싶은 사용자 메일주소
    private String userName; // 비밀번호 찾고 싶은 사용자 이름

    private String id; // 비밀번호 재설정 위한 변수 (uuid)
    private String password; // 재설정한 비밀번호 저장할 변수
}
