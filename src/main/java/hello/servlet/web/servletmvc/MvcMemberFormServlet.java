package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    //여기가 Controller
    //일단 처음에 컨트롤러로 요청이 들어와야돼. 

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);   //getRequestDispatcher  -- 컨트롤러에서 뷰로 이동할때 사용
        //RequestDispatcher 인터페이스의 추상 메소드인 forward() 어딘가 구현되어있다
        dispatcher.forward(request,response);   //이걸 호출하면 이제 서블릿에서 Jsp를 호출할 수 있다.
        //서버내부에서 호출된다.
    }
}
