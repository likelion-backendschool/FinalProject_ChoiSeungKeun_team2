package com.team2.exam.mutbooks.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team2.exam.mutbooks.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String email;

    @Column(unique = true)
    private String nickname;

    private int authLevel;

}