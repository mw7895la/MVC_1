package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="frontControllerServletV1",urlPatterns = "/front-controller/v1/*")     // v1/* v1하위에 어떤게 불려도 여기가 호출된다는 것.
public class FrontControllerServletV1 extends HttpServlet {

    //매핑정보를 만들자  key는 url  value 는 ControllerV1 // 어떤 url이 호출되면 컨트롤러V1을 꺼내서 호출해.
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        System.out.println("@WebServlet이 부착된 부분이 빈으로 등록될 때 생성자 호출");
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
        //미리 url 패턴별로 어디 구현 객체를 쓸지 구현해 놓은것.
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1");

        String requestURI = request.getRequestURI();    // 웹브라우저에서 http://localhost:8080/front-controller/v1/members/new-form을 치면 /front-controller/v1/members/new-form가  그대로 여기 requestURI 에 들어와
        System.out.println("requestURI = " + requestURI);


        ControllerV1 controller = controllerMap.get(requestURI);
        //위에 FrontControllerServletV1 생성자는 WAS시작시 빈으로 등록될 때 생성자 호출이 되었으니 이미 3개의 데이터가 들어가 있다.
        //들어가 있는 상태에서 service로직에서 requestURI를 얻고 그걸 매개변수로 get하면 해당 URI에 맞는 구현객체가 생성 및 반환되어 인터페이스 변수에 저장.
        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);       //해당 인터페이스의 process 메소드가 호출되면서, 위에 controller에 들어온 구현객체의 오버라이딩 메소드가 호출됨.


    }
}


// @ServletComponentScan을 사용하고 있어서 @WebServlet이 붙은 클래스들이 SpringBoot 로드시 빈으로 등록됩니다.
// 그때 빈이 생성될 것이므로 수업 내 코드 기준으로는 @ServletComponentScan으로 인해 @WebServlet이 스캔되고 빈이 등록될 때 생성자가 호출될 것입니다.