package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// /hello로 오면 여기가 실행되는것
@WebServlet(name= "helloServlet", urlPatterns = "/hello")       //name과 urlPatterns 겹치면 안됌.
public class HelloServlet extends HttpServlet {

    @Override       // Crtl + O  로 service 만듬
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청이 오면 service() 가 호출됨.
        //웹 브라우저가 HTTP 요청을 하면 req,resp 객체를 서버가 만들어서 내려준다.
        System.out.println("HelloServlet.service");     //콘솔에 출력
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 웹에서 쿼리파라미터로 username=kim을 해주고 request.getParameter() 해줌.
        System.out.println("username = " + username);

        //응답 메시지 보내기 HTTP 응답 메시지에 데이터가 담겨져서 보냄
        response.setContentType("text/plain");      // 단순 문자로 보낼거고
        response.setCharacterEncoding("utf-8");     // 문자셋 인코딩 정보
        //위 2개는 HTTP Header에 들어가는 정보
        response.getWriter().write("hello " + username);     // write는 HTTP 메시지 바디에 데이터가 들어간다.
    }
}

// org.apache.catalina.connector.RequestFacade@84af29
// 톰캣이나 제티 등등 WAS 서버들이 서블릿 표준 스펙들을 구현하는데 그 구현체들이 찍힌것.

// request와 response가 계속 각각 같은 주소값이 찍힌다.
// 톰캣의 경우 성능 최적화를 위해서 객체를 계속 생성하지 않고, 완전히 사용이 끝나면 RequestFacade 객체를 초기화해서 사용합니다. 그러니까 이미 생성된 객체를 재사용하는 것이지요.
// 그런데 정말로 동시에 요청이 들어오면 다른 객체를 생성해서 사용합니다.