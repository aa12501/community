package com.aa12501.community.controller;

import com.aa12501.community.mapper.QuestionMapper;
import com.aa12501.community.mapper.UserMapper;
import com.aa12501.community.model.Question;
import com.aa12501.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                }
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            model.addAttribute("tag", tag);
            return "publish";
        }
        if(tag.trim().equals("")){
            model.addAttribute("error", "标签不能为空");
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmtModified(System.currentTimeMillis());
        question.setGmtCreate(System.currentTimeMillis());
        question.setTag(tag);
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/";
    }
}
