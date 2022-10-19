package com.team2.exam.mutbooks.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberJoinForm {
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String nickname;
}
