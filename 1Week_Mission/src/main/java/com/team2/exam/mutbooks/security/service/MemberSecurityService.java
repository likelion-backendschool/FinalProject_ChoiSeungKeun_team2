package com.team2.exam.mutbooks.security.service;

import com.team2.exam.mutbooks.member.entity.AuthLevel;
import com.team2.exam.mutbooks.member.entity.Member;
import com.team2.exam.mutbooks.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> loginMember = memberRepository.findByUsername(username);

        if (loginMember.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }

        Member member = loginMember.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (member.getAuthLevel()) {
            case 3 -> authorities.add(new SimpleGrantedAuthority(AuthLevel.NORMAL.toString()));
            case 7 -> authorities.add(new SimpleGrantedAuthority(AuthLevel.ADMIN.toString()));
            default -> authorities.add(new SimpleGrantedAuthority(AuthLevel.AUTHOR.toString()));
        }

        return new User(member.getUsername(), member.getPassword(), authorities);
    }
}
