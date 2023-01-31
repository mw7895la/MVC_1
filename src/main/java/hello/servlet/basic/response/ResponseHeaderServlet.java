package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "reponseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //응답코드를 어떻게 세팅 ??
        //[status -line]
        response.setStatus(HttpServletResponse.SC_OK);//http  응답 코드를 넣을 수 있다.
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);


        //[response-headers]
        //response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        //이 페이지는 진짜 캐시가 되면 안돼! 라고하면 no-cache no-store must-revalidate를 다 넣어줘야 한다.
        response.setHeader("Pragma","no-cache");    //과거 버전까지 캐시를 없애는...
        response.setHeader("my-header","hello");

        //[Header의 편의 메서드]
        //content(response);    //위에선 content type등 직접 세팅 해줬다.
        //cookie(response);
        redirect(response);


        //[Message Body]
        PrintWriter writer = response.getWriter();
        writer.print("ok");


        //Servlet에서 클라이언트의 요청(Request)에 대한 응답(Response) 형태는 문자(character) 또는 바이트(byte)가 될 수 있다.
        //클라이언트의 요청에 문자 형태로 응답하려면 데이터의 흐름(Stream)을 컨트롤 해야 한다. 즉 텍스트(==문자) 형태로 응답을 보내도록 설정해야 한다.
        //HttpServletResponse 인터페이스의 상위 인터페이스인 ServletResponse의 getWriter() 메소드를 호출하면 스트림에 텍스트를 기록하는 것이 가능하다.


    }

    private void content(HttpServletResponse response) {
//Content-Type: text/plain;charset=utf-8
//Content-Length: 2
//response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//response.setContentLength(2); //(생략시 자동 생성)
    }
    private void cookie(HttpServletResponse response) {
//Set-Cookie: myCookie=good; Max-Age=600;
//response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");       //아래와 같은 효과
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
//Status Code 302
//Location: /basic/hello-form.html
//response.setStatus(HttpServletResponse.SC_FOUND); //302
//response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
