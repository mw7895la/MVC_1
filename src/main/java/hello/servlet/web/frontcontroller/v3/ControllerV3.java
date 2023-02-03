package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);     //v1,v2처럼 서블릿에 종속적인거랑 다름.
}
