package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//기능을 확장하더라도 코드를 변경할 필요가 없다. 물론 v3에 이어 v4를 추가해준거 정도는 ok

@WebServlet(name="frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //기존것들과 차이가 있다.
    //private Map<String, ControllerV3> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();      //핸들러가 매핑된 Map 데이터  //여기는 지금 위에와 달리 모든 ControllerVX가 들어갈 수 있따.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {     //서블릿이 빈으로 등록되면서 생성자 호출시 아래 메소드 2개가 호출된다.
        //기존 처럼 매핑 정보 넣어라               //지금 handlerMappingMap의 value가 Object다 어떤 자바 객체든지 Object에 담긴다. 배열,리스트, 맵... ..
        initHandlerMappingMap();

        initHandlerAdapters();      //MyHandlerAdapter의 구현체를 넣어줌.
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    //---------- 위 까지가 초기화 -------------//


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //일단 handlerMappingMap 꺼내야돼

        System.out.println(request.getRequestURI());

        //MemberFormControllerV3() 처음에 이게 반환됨
        Object handler = getHandler(request);   // 그림에서 1번이다 getHandler() 핸들러 찾아와  찾아왔어.
        //FrontControllerServletV5 다 작성하고 테스트를 했는데 404 Error났음.  즉 핸들러를 못찾은거다.
        if(handler ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //instanceof검사 후 ControllerV3HandlerAdapter 반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler);      //그림에서 2번의 핸들러를 처리 할 수 있는 핸들러 어댑터 찾아와  찾아왔어.

        //ControllerV3HandlerAdapter에 있는 handle 호출 여기서 파라미터로 handler 는 MemberFormControllerV3() 이다.
        ModelView mv = adapter.handle(request, response, handler);        // 그림에서 4번 5번 의 과정. handler() 호출

        //view Resolver를 만들어줘야해
        String viewName = mv.getViewName();//논리이름 ex) new-form만 있어.
        MyView view = viewResolver(viewName);                               //그림에서 6번


        //Model도 render에 같이 넘겨줘야한다.
        view.render(mv.getModel(),request,response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //핸들러를 v3냐 v4냐 목록을 뒤져서 해당 핸들러 어댑터를 찾는 곳     //핸들러 어댑터 목록
        //현재 핸들러 MemberFormControllerV3()
        System.out.println(handlerAdapters.size());
        for (MyHandlerAdapter adapter : handlerAdapters) {          //지금은 ControllerV3HandlerAdapter 하나만 있어서.. 1개       //MyHandlerAdapter adapter : handlerAdapters는 리스트 하나하나 구현객체를 인터페이스 변수에 대입해준거나 같음.
            //만약 어댑터가 핸들러를 지원하느냐? ( 목록중에 해당 V3핸들러(컨트롤러)가 있느냐? )
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler ="+handler);   //파라미터로 넘어온 핸들러가 뭔지 남겨주는거.
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();    // 웹브라우저에서 http://localhost:8080/front-controller/v1/members/new-form을 치면 /front-controller/v1/members/new-form가  그대로 여기 requestURI 에 들어와
        return handlerMappingMap.get(requestURI);        //initHandlerMappingMap에서 해당 URI를 찾아서 구현체 넣어

    }
}
