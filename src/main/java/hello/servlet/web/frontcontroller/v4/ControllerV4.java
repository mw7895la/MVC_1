package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**     슬래쉬 * * 치고 엔터하면 아래처럼 나옴
     *
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String,String> paramMap, Map<String,Object>model);
}
