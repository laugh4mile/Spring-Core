package spring.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
        @Component 가 붙으면 컴포넌트 스캔을 통해 자동으로 bean으로 등록된다.
        AppConfig 처럼 의존관계를 명시하기위해선 생성자 위에 @Autowired를 써야한다.
        @Autowired는 의존관계를 자동으로 주입해준다.
        이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다. (MemberRepository)
        ac.getBean(MeberRepository.class); 가 자동으로 들어간다고 생각하자.
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}