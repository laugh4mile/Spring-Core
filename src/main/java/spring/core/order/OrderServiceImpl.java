package spring.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService{
    /*
        1. 생성자에서 값을 입력 안하면 final의 특성상 초기화를 해야하기 때문에 컴파일에러가 뜸
        2. 생성자 주입방식만 final 키워드를 사용할 수 있다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // 수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    /*
        중요! 생성자가 1개일 경우 @Autowired를 생략해도 자동 주입된다.
        생성자로 의존관계를 주입할 경우 객체가 생성됨과 동시에 의존관계가 주입된다.
        즉, 다른 의존성 주입과 다르게 생성자 주입은 스프링빈 등록과 의존관계주입이 동시에된다.
     */

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 일반 메서드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
