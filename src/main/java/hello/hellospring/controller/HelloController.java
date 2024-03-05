package hello.hellospring.controller;

import org.springframework.ui.Model; // 올바른 Model 클래스를 import합니다
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","이곳의 데이터가 data라는 이름으로 넘겨지는 것임.");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString (@RequestParam("name") String name){
        return "hello "+name;
        //Responsebody가 body에 직접 넣어주겠다는 의미라서 적으면 그대로 올라감
        //쓸일 거의 없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //문자가 아닌 객체를 넘김
        //이게 json방식임 키와 벨류로 이루어짐
        //객체를 반환하고 responsebody하면 자연스럽게 json사용함
        //객체가 오면 기본 디폴트값은 그냥 json방식으로 데이터를 만들어서 http응답에 반환하겠다.
        //기존에는 viewresolver가 동작 이 역할을 @responsebody태그가 붙으면 httpmessageconverter가 함
        //단순문자면 Stringconverter가 응답을 함
        //객체면 jsonconverter가 응답을 함
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {

            this.name = name;
        }
    }
}
