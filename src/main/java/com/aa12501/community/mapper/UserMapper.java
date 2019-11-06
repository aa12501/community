package com.aa12501.community.mapper;

import com.aa12501.community.model.User;
import org.apache.ibatis.annotations.*;

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

    @Update("update t_user set " +
            "token=#{token}, gmt_modified=#{gmtModified}, avatar_url=#{avatarUrl}, name=#{name} " +
            "where account_id = #{accountId}")
    void update(User user);

    @Select("select * from t_user where account_id = #{accountId}")
    User finfByAccountId(@Param("accountId") String accountId);
}
