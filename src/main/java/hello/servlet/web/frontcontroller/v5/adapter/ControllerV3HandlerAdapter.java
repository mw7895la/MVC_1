package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //MemberFormControllerV3() 처음 핸들러
        return (handler instanceof ControllerV3);
    }//핸들러(컨트롤러)가 넘어올거야  일단 여기선 V3의 인스턴스냐 라고 확인

    //frontController에서 supports 호출하고 걸러진 애를 찾아서 그다음에 handle를 호출한다.

    @Override           //Object handler = MemberFormControllerV3()
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //Object로 한 이유는 유연하게 하려고
        ControllerV3 controller = (ControllerV3) handler;       //캐스팅 해도 괜찮다  이미 서포트에서 ControllerV3 타입의 인스턴스인지 확인했기 때문에

        Map<String,String>paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);        //ctrl alt b 눌르면 어떤 구현체들이 있는지 볼 수 있음.

        return mv;
    }
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String>paramMap=new HashMap<>();     //getParameterNames() 는 username, age 2개 이걸 paramName으로 받아서 하나씩 put
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
        //age는 나중에 SaveControllerv3 구현체로 가서 int로 바꿔주는 작업이 있다.
    }
}
