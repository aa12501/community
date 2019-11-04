package com.aa12501.community.mapper;

import com.aa12501.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * t_user表的查询mapper
 */

@Mapper
public interface UserMapper {
    @Insert("insert into t_user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from t_user where token =#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from t_user where id = #{id}")
    User findById(@Param("id") Integer id);
}
