package spring.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import spring.core.member.Member;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    // 주입할 스프링 빈이 없어도 동작해야할 때가 있다.
    // 아래의 Member는 스프링이 관리하는 객체가 아니다. 따라서 @Autowired를 쓰면 NoSuchBeanDefinitionException 이 발생한다.
    // 자동 주입 대상을 옵션으로 처리하는 3가지 방법이 있다.
    static class TestBean{
        // 자동주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동주입할 대상이 없으면 null이 입력
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        // 자동주입할 대상이 없으면 Optional.empty가 입력됨
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
