package spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.core.discount.DiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration
@ComponentScan(
        basePackages = "spring.core",
        /*
            basePackages : 탐색할 패키지의 시작 위치를 지정. 하위 패키지까지 모두.

            {} 로 묶어 여러개의 시작 위치를 지정할 수도 있다.

            만약 지정하지 않으면 @ComponentScan이 붙은 설정 클래스의 패키지가 시작위치이다.(spring.core)
            따라서 설정 정보 클래스의 위치는 프로젝트의 최상단에 두는것이 관례이다.

            사실 SpringBoot를 쓰면 프로젝트 생성시 최상단에 xxxApplication 클래스가 자동으로 생성되는데 (CoreApplication.class)
            여기에 @SpringBootApplication 어노테이션에 @ComponentScan이 이미 포함되어있다.
            SpringBoot를 쓰면 ComponentScan을 쓸 필요도 없는것이다.
         */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/*
    컴포넌트 스캔을 사용하면 @Component 어노테이션이 붙은 클래스를 스캔에서 스프링 빈으로 등록한다.
    따라서 기존에 @Configuration 이 붙은 AppConfig, TestConfig 등이 함께 등록되고 실행된다. (@Configuration 를 까보면 @Component가 포함되어있다.)
    원활한 테스트를 위해 excludeFilters를 사용하여 @Configuration이 붙은 클래스들을 컴포넌트 스캔 대상에서 제외하자.

    Q. @ComponentScan 으로 @Configration 을 대체가능한데 왜 @Configration을 썻나?
    A. 사실 @ComponentScan으로도 빈들을 등록하기 때문에 @Configration을 안써도 된다. 하지만 보통 스프링에선
        설정파일들은 관례상 @Configuration을 붙인다. 이 클래스는 설정파일이에요~ 하는 표시용 이라고 생각하자.

    Q. 근데 얘도 @Configuration 이 붙었는데 excludeFilter에 걸려 스캔 안하는거 아닌가?
    A. 생성자를 보면 AutoAppConfig를 넘기고 있다. 설정이 시작되는 파일인데 제외될리가있나
 */
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}