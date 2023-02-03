package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 자동으로 스프링 빈으로 등록한다. 내부에 @Component가 있기 때문.
// 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다. -> @Controller가 있으면 RequestMappingHandlerMapping가 이거는 핸들러 정보구나 하고 꺼낼 수 있는 대상이 된다.  그림의 1번
//RequestMappingHandlerMapping입장에서 이게 내가 인식할 수 있는 핸들러야 아니야를 어떤식으로 찾냐면,
// 스프링 빈 중에서 @RequestMapping 또는 @Controller 가 클래스 레벨에 붙어있으면 매핑 정보로 인식한다. 찾아주면 (그림의 2번) RequestMapping HandlerAdapter가 핸들러 매핑에서 준 애를(SpringMemberFormControllerV1) 실행한다. (그림의 3번부터 )
//@Component      //컴포넌트 스캔을 통해 스프링 빈으로 등록
//@RequestMapping
//클래스 레벨에 @Controller만 적고 아래 메소드에 @RequestMapping을 적거나 //  클래스 레벨에 @Component와@RequestMapping을 적고 아래 메소드에도 @RequestMapping을 적어도 된다.
public class SpringMemberFormControllerV1 {
//RequestMappingHandlerAdapter 가 support()및 handle() 메서드를 실행하여 여기 핸들러를 호출 하고 ModelAndView를 반환한다.
// abstract AbstractHandlerMethodAdapter 가 인터페이스 HandlerAdapter의 supports(),handle()메소드를 구현하고 있다 RequestMappingHandlerAdapter는 부모클래스인
//AbstractHandlerMethodAdapter의 supports(), 및 handle() 메소드를 사용한 것.
    @RequestMapping("/springmvc/v1/members/new-form")           //ModelAndView는 스프링이 제공한다.
    public ModelAndView process(){
        return new ModelAndView("new-form");            //뷰 리졸버에서 Application.properties에 적힌  prefix와 suffix정보를 갖고 jsp를 처리 하기위한 뷰가 찾아져서 랜더가 된다.
    }


}
