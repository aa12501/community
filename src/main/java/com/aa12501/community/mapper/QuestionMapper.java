package com.aa12501.community.mapper;

import com.aa12501.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * t_question表的查询mapper
 */

@Mapper
public interface QuestionMapper {

    @Insert("insert into t_question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Update("update t_question " +
            "set title=#{title}, description=#{description}, tag=#{tag}, gmt_modified=#{gmtModified} " +
            "where id=#{id}")
    void update(Question question);

    @Select("select * from t_question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from t_question")
    Integer count();

    @Select("select * from t_question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from t_question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from t_question where id = #{id}")
    Question getById(@Param("id") Integer id);
}
