package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHtmlServlet",urlPatterns ="/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //응답을 html로 나갈거야
        //content-type: text/html:charset=utf-8
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕</div>");
        writer.println("</body>");
        writer.println("</html>");
        //PrintWriter 클래스는 간단한 설명하자면, 바이트를 문자 형태를 가지는 객체로 바꿔준다. 클라이언트에게 문자 형태로 응답을 하고 싶기 때문에 writer이라는 PrintWriter 클래스 객체를 정의하고 getWriter() 메소드를 통해 인스턴스를 얻었다.
        //getWriter() 메소드를 통해 응답으로 내보낼 출력 스트림을 얻어낸 후 writer.print(HTML 태그) 형태로 작성하여 스트림에 텍스트를 기록한다.

        //웹 브라우저가 제대로 렌더링 했는지 확인


    }
}
