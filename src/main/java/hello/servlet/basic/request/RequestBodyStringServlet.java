package hello.servlet.basic.request;


import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();      //메시지 바디의 내용을 바이트 코드로 바로 얻을 수 있음.
        //ServletInputStream 객체는 클라이언트로 부터 오는 데이터를 읽을 수 있게 해준다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스프링이 StreamUtils를 제공한다. inputStream 바이트에서 문자로 바꿀때 인코딩 정보 알려줘야돼

        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("OK");
        //Postman으로 테스트.
    }
}
