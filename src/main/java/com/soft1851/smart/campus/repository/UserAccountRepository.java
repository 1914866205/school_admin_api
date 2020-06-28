package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author xunmi
 * @ClassName UserAccountRepository
 * @Description 用户账号
 * @Date 2020/5/29
 * @Version 1.0
 **/
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    /**
     * 通过教工号查找出教师名称
     *
     * @param jobNumber
     * @return
     */
    @Query(value = "SELECT user_name FROM user_account WHERE job_number = ?1", nativeQuery = true)
    String getUserName(String jobNumber);

    /**
     * 根据id查用户数据
     * @param id
     * @return
     */
    UserAccount findByPkUserAccountId(String id);

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10,rollbackFor = RuntimeException.class)
    @Query("update UserAccount v set v.isDeleted = true where v.pkUserAccountId in ?1")
    void deleteBatchByUserAccount(List<String> ids);



    /**
     * 根据手机号码查询用户数据
     *
     * @param cardNumber String
     * @return Optional<UserAccount>
     */
    @Query(value = "select * from user_account as ua where ua.card_number=?1 ", nativeQuery = true)
    Optional<UserAccount> findByCardNumber(String cardNumber);

    /**
     * 根据卡号查找用户
     * @param cardNumber
     * @return
     */
    UserAccount findUserAccountByCardNumber(String cardNumber);

    /**
     * 修改用户账号信息
     * @param userAccount
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE UserAccount SET nickname=:#{#userAccount.nickname}, " +
            "phoneNumber=:#{#userAccount.phoneNumber}, address=:#{#userAccount.address} " +
            "WHERE pkUserAccountId=:#{#userAccount.pkUserAccountId} ")
    int updateUserAccountById(@Param("userAccount") UserAccount userAccount);

    /**
     * 修改用户状态
     * @param pkUserAccountId
     * @param status
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    /*@Query(value = "UPDATE UserAccount SET status=:#{#userAccount.status} " +
            "WHERE pkUserAccountId=:#{#userAccount.pkUserAccountId} ")
    int updateStatusById(@Param("userAccount") UserAccount userAccount);*/

    @Query(value = "update first_smart_campus.user_account set status=?2 " +
            "where pk_user_account_id=?1",nativeQuery = true)
    int updateStatusById(String pkUserAccountId,Boolean status);


    /**
     * 逻辑删除
     * @param pkUserAccountId
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE first_smart_campus.user_account SET is_deleted = true WHERE pk_user_account_id=?1",nativeQuery = true)
    void deleteUserAccount(String pkUserAccountId);

    /**
     * 修改班级id
     * @param clazzId
     * @param collection
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE UserAccount SET clazzId=?1 WHERE pkUserAccountId in ?2 ")
    int updateClazzIdById(long clazzId, List<String> collection);

    /**
     * 分页查询所有用户
     * @param pageable
     * @return
     */
    @Query(value = "select * from first_smart_campus.user_account where is_deleted = false",nativeQuery = true)
    Page<UserAccount> getAllUserAccount(Pageable pageable);
}
