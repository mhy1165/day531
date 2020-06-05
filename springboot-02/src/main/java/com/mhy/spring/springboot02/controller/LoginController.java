package com.mhy.spring.springboot02.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

   // @RequestMapping(value="/user/login",method= RequestMethod.POST) 等价于

//    除了@PostMapping 还有@GetMapping 还有 @DeleteMapping
    //@RequestParm 从请求参数中获取.获取不到值会报错
@PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map/*用于存放信息*/
                , HttpSession session){

        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //StringUtils是增强版的String 输入参数 String 为 null 则不会抛出

            //存用户名，作为拦截器的判断的依据
            session.setAttribute("loginUser",username);

            //防止表单重复提交==> 重定向redirect
            return "redirect:/main.html";
            // 接受到/user/login，并且名字和密码没有错误，重定向到/main.html，main.html通过ViewController(MyMvcConfig)的视图映射来到dashboard.html，

        }else{
            //放入错误消息
            map.put("msg","用户名密码错误");
            return "/login";
        }



    }

}
