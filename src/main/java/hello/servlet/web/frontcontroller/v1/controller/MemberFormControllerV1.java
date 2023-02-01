package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);   //getRequestDispatcher  -- 컨트롤러에서 뷰로 이동할때 사용
        //RequestDispatcher 인터페이스의 추상 메소드인 forward() 어딘가 구현되어있다
        dispatcher.forward(request,response);   //이걸 호출하면 이제 서블릿에서 Jsp를 호출할 수 있다.
        //서버내부에서 호출된다.
    }
}
