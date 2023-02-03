package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    //핸들러 매핑 정보에서 컨트롤러가 넘어왔을 때 지원할수 있는지 확인.
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler)throws ServletException, IOException;

    //그러면 인터페이스 만들었으니 이제 ControllerV3를 지원하는 어댑터를 만들어보자.
}
