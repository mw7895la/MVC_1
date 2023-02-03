package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")        //공통되는 RequestMapping경로 따로 뺴줌.
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")           //ModelAndView는 스프링이 제공한다.
    public ModelAndView newForm(){
        return new ModelAndView("new-form");            //뷰 리졸버에서 Application.properties에 적힌  prefix와 suffix정보를 갖고 jsp를 처리 하기위한 뷰가 찾아져서 랜더가 된다.
    }

    @RequestMapping     //그럼 여기는? 어떻게 줄이냐 그냥 ( )를 싹 지워 더이상 클래스에 붙여준 경로에 더해줄게 없으니까..
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");        //jsp의 이름이 members여서 .. 헷갈리지 말것.
        //mv.getModel().put("members",members);     //아래가 더 편한 메소드
        mv.addObject("members",members);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");     //Map에다가 요청파라미터 정보를 다 넘겨서 여기선 쓰기만 하면 된다.
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member",member);     //필드에서 초기화해준 Map을 가져와서 member 넣어줌.
        mv.addObject("member",member);  //더 편한 메소드.
        return mv;

    }
}
