package com.aa12501.community.mapper;

import com.aa12501.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * t_question表的查询mapper
 */

@Mapper
public interface QuestionMapper {

    @Insert("insert into t_question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from t_question limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);
}