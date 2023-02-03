package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="frontControllerServletV4",urlPatterns = "/front-controller/v4/*")     // v2/* v2하위에 어떤게 불려도 여기가 호출된다는 것.
public class FrontControllerServletV4 extends HttpServlet {

    //매핑정보를 만들자  key는 url  value 는 ControllerV4 // 어떤 url이 호출되면 컨트롤러V1을 꺼내서 호출해.
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        System.out.println("@ServletComponentScan에 의해 @WebServlet어노테이션이 붙은 부분이 빈으로 등록될 때 생성자 호출");
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
        //미리 url 패턴별로 어디 구현 객체를 쓸지 구현해 놓은것.
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4");

        String requestURI = request.getRequestURI();    // 웹브라우저에서 http://localhost:8080/front-controller/v1/members/new-form을 치면 /front-controller/v1/members/new-form가  그대로 여기 requestURI 에 들어와
        System.out.println("requestURI = " + requestURI);


        ControllerV4 controller = controllerMap.get(requestURI);
        //위에 FrontControllerServletV2 생성자는 WAS시작시 빈으로 등록될 때 생성자 호출이 되었으니 이미 3개의 데이터가 들어가 있다.
        //들어가 있는 상태에서 service로직에서 requestURI를 얻고 그걸 매개변수로 get하면 해당 URI에 맞는 구현객체가 생성 및 반환되어 인터페이스 변수에 저장.
        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        Map<String, Object> model = new HashMap<>();        // V4에서 추가된 부분
        String viewName = controller.process(paramMap, model);


        MyView view = viewResolver(viewName);


        view.render(model,request,response);

    }

    //jsp 경로 매핑 완성시켜주는 viewResolver
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        /*Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println("name : " + request.getParameter(name));
        }
*/
        Map<String,String>paramMap=new HashMap<>();     //getParameterNames() 는 username, age 2개 이걸 paramName으로 받아서 하나씩 put
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
        //age는 나중에 SaveControllerv3 구현체로 가서 int로 바꿔주는 작업이 있다.
    }
}

