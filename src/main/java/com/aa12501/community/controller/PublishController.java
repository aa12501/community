package com.aa12501.community.controller;

import com.aa12501.community.dto.QuestionDTO;
import com.aa12501.community.mapper.QuestionMapper;
import com.aa12501.community.model.Question;
import com.aa12501.community.model.User;
import com.aa12501.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{questionId}")
    public String edit(@PathVariable(name = "questionId") Integer questionId,
                       Model model) {
        QuestionDTO question = questionService.getById(questionId);
        if (question == null) {
            //这里等有空了之后再搞吧，现在不搞了
        }

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            model.addAttribute("tag", tag);
            return "publish";
        }
        if (tag.trim().equals("")) {
            model.addAttribute("error", "标签不能为空");
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtModified(System.currentTimeMillis());

        questionService.createOrUpdate(question, user);

//        questionMapper.create(question);
        return "redirect:/";
    }
}
