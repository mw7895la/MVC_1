package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String,String>paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        //V# 인터페이스에 맞게끔. 컨트롤러는 뷰 네임을 스트링으로 반환해, 어댑터가 어댑팅을 ModelView로 하는거지
        String viewName=controller.process(paramMap,model);     //지금 V3이랑 V4는 실제 Form List Save 리턴 타입이 달라

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String>paramMap=new HashMap<>();     //getParameterNames() 는 username, age 2개 이걸 paramName으로 받아서 하나씩 put
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
        //age는 나중에 SaveControllerv3 구현체로 가서 int로 바꿔주는 작업이 있다.
    }
}
