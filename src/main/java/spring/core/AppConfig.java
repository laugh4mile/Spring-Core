package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    /*
    컴포넌트 스캔을 하면
    @Bean memberService()와 @Bean orderService()에서 memberRepository()를 호출한다.
    그렇다면 new MemoryMemberRepository() 를 2번 하므로 싱글톤이 깨지는 것 아닌가??

    예상
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
    call AppConfig.memberRepository
    call AppConfig.memberRepository

    실제 결과 : 모두 1번만 호출이 된다.
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService

    ConfigurationSingletonTest.configurationDeep() 참조.
    @Configuration을 지우면 싱글톤이 깨지고 memberRepository가 3번 호출될 것이다.

    고민하지말고 스프링 설정 정보는 항상 @Configuration 을 사용하자
     */
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
