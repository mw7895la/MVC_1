package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    //JSON을 HelloData 객체로 파싱 하기
    private ObjectMapper objectMapper = new ObjectMapper();     //스프링이 기본으로 제공해준다
    //ObjectMapper 란???
    //JSON 컨텐츠를 Java 객체로 deserialization 하거나 Java 객체를 JSON으로 serialization 할 때 사용하는 Jackson 라이브러리의 클래스이다.



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();      //HTTP 메시지 바디에 있는거 가져온거야
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);
        //Postman통해서 테스트.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //objectMapper.readValue(객체로변환 시킬 json형식, 객체 타입)

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());        //객체로 잘 변환이 되었다.

        response.getWriter().write("ok");
    }
}
