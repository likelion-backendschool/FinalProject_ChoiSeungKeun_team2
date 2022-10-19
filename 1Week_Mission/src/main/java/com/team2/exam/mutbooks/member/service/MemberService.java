package com.team2.exam.mutbooks.member.service;

import com.team2.exam.mutbooks.base.exception.AlreadyExistingMemberException;
import com.team2.exam.mutbooks.base.exception.AlreadyExistingNicknameException;
import com.team2.exam.mutbooks.member.entity.AuthLevel;
import com.team2.exam.mutbooks.member.entity.Member;
import com.team2.exam.mutbooks.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(String username, String password, String email, String nickname) {
        Optional<Member> alreadyExistingMember = memberRepository.findByUsername(username);
        Optional<Member> alreadyExistingNickname = memberRepository.findByNickname(nickname);

        if (alreadyExistingMember.isPresent()) {
            throw new AlreadyExistingMemberException("이미 존재하는 회원입니다.");
        }

        if (alreadyExistingNickname.isPresent()) {
            throw new AlreadyExistingNicknameException("이미 사용중인 작가명입니다.");
        }

        int authLevel = checkAuthLevel(nickname);

        Member createMember = Member
                .builder()
                .username(username)
                .password(password)
                .email(email)
                .nickname(nickname)
                .authLevel(authLevel)
                .build();

        Member savedMember = memberRepository.save(createMember);

        return savedMember;
    }

    private int checkAuthLevel(String nickname) {
        int authLevel = AuthLevel.NORMAL.getAuthLevel();

        if (nickname != null && nickname.length() > 0) {
            authLevel = AuthLevel.AUTHOR.getAuthLevel();
        }

        return authLevel;
    }
}
