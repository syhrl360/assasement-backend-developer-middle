package com.middle.assessment.paymentservice.mapper;

import com.middle.assessment.paymentservice.dto.PaymentRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {

    @Select("SELECT id as id, user_id as userId, order_id as orderId, name as name, bank_account as bankAccount, bank_name as bankName, repay_amount as repayAmount, admin_fee as adminFee, due_date as dueDate FROM payment_record WHERE user_id = #{userId}")
    PaymentRecord findByUserId(Long userId);

    @Select("SELECT id as id, user_id as userId, order_id as orderId, name as name, bank_account as bankAccount, bank_name as bankName, repay_amount as repayAmount, admin_fee as adminFee, due_date as dueDate FROM payment_record")
    List<PaymentRecord> findAll();

    @Insert("INSERT INTO payment_record (user_id, order_id, name, bank_account, bank_name, repay_amount, admin_fee, due_date) VALUES (#{userId}, #{orderId}, #{name}, #{bankAccount}, #{bankName}, #{repayAmount}, #{adminFee}, #{dueDate}))")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(PaymentRecord paymentRecord);

    @Update("UPDATE payment_record SET user_id = #{userId}, order_id = #{orderId}, name = #{name}, bank_account = #{bankAccount}, bank_name = #{bankAccount}, repay_amount = #{repayAmount}, admin_fee = #{adminFee}, due_date = #{dueDate}  WHERE id = #{id}")
    void update(PaymentRecord paymentRecord);

    @Delete("DELETE FROM payment_record WHERE id = #{id}")
    void delete(Long id);
}
