package com.middle.assessment.loanservice.mapper;

import com.middle.assessment.loanservice.dto.LoanRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoanMapper {

    @Select("SELECT id as id, user_id as userId, order_id as orderId, name as name, loan_amount as loanAmount, loan_date as loanDate, description as description FROM loan_record WHERE user_id = #{userId}")
    LoanRecord findByUserId(Long userId);

    @Select("SELECT id as id, user_id as userId, order_id as orderId, name as name, loan_amount as loanAmount, loan_date as loanDate, description as description FROM loan_record")
    List<LoanRecord> findAll();

    @Insert("INSERT INTO loan_record (user_id, order_id, name, loan_amount, loan_date, description) VALUES (#{userId}, #{orderId}, #{name}, #{loanAmount}, CURRENT_TIMESTAMP(), #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(LoanRecord loanRecord);

    @Update("UPDATE loan_record SET user_id = #{userId}, order_id = #{orderId}, name = #{name}, loan_amount = #{loanAmount}, loan_date = #{loanDate}, description = #{description} WHERE id = #{id}")
    void update(LoanRecord loanRecord);

    @Delete("DELETE FROM loan_record WHERE id = #{id}")
    void delete(Long id);
}


