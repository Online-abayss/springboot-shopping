package com.example.springboot_shopping.Service;

import com.example.springboot_shopping.entity.Member;
import com.example.springboot_shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {

        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {

        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null)
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
    }
}
