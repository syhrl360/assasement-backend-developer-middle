package com.middle.assessment.accountservice.mapper;

import com.middle.assessment.accountservice.dto.UserAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM user_account WHERE user_id = #{userId}")
    UserAccount findByUserId(Long userId);

    @Select("SELECT * FROM user_account")
    List<UserAccount> findAll();

    @Insert("INSERT INTO user_account (user_id, name, bank_account, bank_name, balance) VALUES (#{userId}, #{name}, #{bankAccount}, #{bankName}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserAccount userAccount);

    @Update("UPDATE user_account SET user_id = #{userId}, name = #{name}, bank_account = #{bankAccount}, bank_name = #{bankName}, balance = #{balance} WHERE id = #{id}")
    void update(UserAccount userAccount);

    @Delete("DELETE FROM user_account WHERE id = #{id}")
    void delete(Long id);
}
