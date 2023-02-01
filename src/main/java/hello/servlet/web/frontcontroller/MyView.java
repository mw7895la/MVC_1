package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {
    //v1 뿐만 아니라 v2등등에서도 사용할거라 frontcontroller 패키지 바로 밑에 만들었다.
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //view가 실제 랜더링 되도록 동작하는것
    public void render(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
}
