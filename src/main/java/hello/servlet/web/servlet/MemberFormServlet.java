package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="memberFormServlet",urlPatterns="/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    private MemberRepository memberRepository =MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //요청 데이터는 쓸건 없고 메시지 응답으로 html이 나가야 되니까 content body를 잡아줘야함.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w =response.getWriter();        // 서블릿으로 들어온 요청은 대체로 HTML로 응답을 내보내기 때문에 response는 내부에 HTTP 메시지 바디에 직접 데이터를 입력할 수 있는 PrintWriter라는 것을 가지고 있습니다.
        //response.getWriter().println(...);으로 작성해도 되지만  텍스트 출력 스트림 PrintWriter 객체로 받아 해도 된다.
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
    //지금 실행하면 오류남 /servet/members/save를 안만들어줬기 때문.
}
