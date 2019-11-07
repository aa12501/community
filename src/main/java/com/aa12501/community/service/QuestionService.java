package com.aa12501.community.service;

import com.aa12501.community.dto.PaginationDTO;
import com.aa12501.community.dto.QuestionDTO;
import com.aa12501.community.mapper.QuestionMapper;
import com.aa12501.community.mapper.UserMapper;
import com.aa12501.community.model.Question;
import com.aa12501.community.model.QuestionExample;
import com.aa12501.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        //获得总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //页数错误的处理
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        paginationDTO.setPagination(totalPage, page, size);

        Integer offset = size * (page - 1);     //下标
        //通过下标去数据库里查找相应的几条数据
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            Integer id = question.getCreator();
            User user = userMapper.selectByPrimaryKey(id);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        //获得总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //页数错误的处理
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        paginationDTO.setPagination(totalPage, page, size);

        Integer offset = size * (page - 1);     //下标
        //通过下标去数据库里查找相应的几条数据
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            Integer id = question.getCreator();
            User user = userMapper.selectByPrimaryKey(id);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question, User creator) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setCreator(creator.getId());

            questionMapper.insertSelective(question);

        } else {
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }
}
