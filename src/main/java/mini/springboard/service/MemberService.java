package mini.springboard.service;

import lombok.RequiredArgsConstructor;
import mini.springboard.domain.Member;
import mini.springboard.repository.JpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    @Autowired
    private final JpaMemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param username
     * @param email
     * @param password
     * @return
     */
    public Member Signup(String username, String email, String password){
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        this.memberRepository.save(member);
        return member;
    }

    public Member getMember(String username){
        Optional<Member> member = this.memberRepository.findByusername(username);
        if (member.isPresent()){
            return member.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }



    //로그인


}
