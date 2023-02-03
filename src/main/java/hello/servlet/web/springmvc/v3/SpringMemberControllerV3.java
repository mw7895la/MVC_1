package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form",method = RequestMethod.GET)           //ModelAndView는 스프링이 제공한다.
    @GetMapping("/new-form")
    public String newForm(){    //ModelView -> String 타입변환
        return "new-form";
        //v2 버전//return new ModelAndView("new-form");            //뷰 리졸버에서 Application.properties에 적힌  prefix와 suffix정보를 갖고 jsp를 처리 하기위한 뷰가 찾아져서 랜더가 된다.
    }

    //@RequestMapping(method=RequestMethod.GET)     //그럼 여기는? 어떻게 줄이냐 그냥 ( )를 싹 지워 더이상 클래스에 붙여준 경로에 더해줄게 없으니까..
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);
        //ModelAndView mv = new ModelAndView("members");        //jsp의 이름이 members여서 .. 헷갈리지 말것.

        //mv.getModel().put("members",members);     //아래가 더 편한 메소드
        //mv.addObject("members",members);

        return "members";       //jsp의 논리 경로
    }

    //@RequestMapping(value="/save",method=RequestMethod.POST)///*HttpServletRequest request, HttpServletResponse response*/) 였던걸 변경
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username, @RequestParam("age") int age,Model model){
        /*String username = request.getParameter("username");     //Map에다가 요청파라미터 정보를 다 넘겨서 여기선 쓰기만 하면 된다.
        int age = Integer.parseInt(request.getParameter("age"));*/      //v2에서 했던 것.

        Member member = new Member(username, age);
        memberRepository.save(member);


        model.addAttribute("member",member);
        //ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member",member);     //필드에서 초기화해준 Map을 가져와서 member 넣어줌.
        //mv.addObject("member",member);  //더 편한 메소드.
        return "save-result";

    }
}
