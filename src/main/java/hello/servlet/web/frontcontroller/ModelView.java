package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//서블릿의 종속성을 제거하기 위해 Model을 직접 만들고, 추가로 View 이름까지 전달하는 객체를
//만들어보자.

public class ModelView {
    private String viewName;        //view의 논리적 이름
    private Map<String,Object> model = new HashMap<>();     // 나중에 JSP에서 쓸 수 있도록 후 처리를 해줄 것임.

    public ModelView(String viewName) {
        this.viewName = viewName;
    }



    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
