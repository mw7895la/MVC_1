package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //Model에 있는 값을 JSP로 넘겨야돼.

        modelToRequestAttribute(model, request);
        //jsp는 request에 담긴 데이터를 조회하기 때문에 model의 데이터를 request로 옮김
        //HttpServletRequest 의 setAttribute에 넣어야 jsp의 표현식들로 jsp에서 편하게 쓸 수 있기 때문.


        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }
    //render가 오면 model에 있는 값을 다 꺼내서  request 에 다 담아놔

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value)-> request.setAttribute(key,value));
        //model에 있는 데이터를 다 꺼내라 변수를 key ,value로 해서 루프를 다 돌린다. //request에 값을 다 담아놔
    }
}
