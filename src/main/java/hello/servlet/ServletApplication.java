package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;


@ServletComponentScan		//스프링이 자동으로 현재 내 패키지 포함 하위 다 뒤져서 서블릿 다 뒤져서 서블릿을 다 등록해준다.
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
/*
	@Bean
	SpringMemberFormControllerV1 SpringMemberFormControllerV1(){
		return new SpringMemberFormControllerV1();
	}	//이렇게 수동으로 등록해도 된다. 내가 알고 있는 AnnotationConfigApplicationContext(~.class)에 넘기지 않았지만 바로 위에 SpringApplication.run(ServletApplication.class,args)가 같은 역할을 한다.
	*/
}
