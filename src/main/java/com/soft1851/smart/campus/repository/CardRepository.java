package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.SysCard;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CardRepository
 * @Description TODO
 * @Author 田震
 * @Date 2020/5/26
 **/
public interface CardRepository extends JpaRepository<SysCard, Long> {
    /**
     * 自定义分页查询
     *
     * @param pageable
     * @return
     */
    @Query("select u from SysCard u")
    Page<SysCard> findALL(Pageable pageable);

    /**
     * 查询一卡通所有信息
     *
     * @return
     */
    @Query("select u.isDeleted from SysCard u")
    SysCard getAll();

    /**
     * 根据Id查询一卡通信息
     *
     * @param id
     * @return
     */
    SysCard findByPkCardId(Long id);

    /**
     * 逻辑删除 根据id删除一卡通信息
     *
     * @param pkCardId
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update sys_card set is_deleted = true where pk_card_id = ?1", nativeQuery = true)
    void deleteByPkCardId(Long pkCardId);

    /**
     * 批量逻辑删除
     *
     * @param ids
     */
    @Modifying
    @Transactional(timeout = 10, rollbackFor = RuntimeException.class)
    @Query("update SysCard v set v.isDeleted = true where v.pkCardId in ?1")
    void deleteBatch(List<Long> ids);

    /**
     * 根据卡号查询一卡通信息
     *
     * @param cardNumber
     * @return
     */
    SysCard findByCardNumber(String cardNumber);

    /**
     * 状态激活
     *
     * @param pkCardId
     * @param status
     * @return
     */
    @Modifying
    @LastModifiedBy
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update sys_card set status = ?2 where pk_card_id = ?1", nativeQuery = true)
    int updateStatus(Long pkCardId, Boolean status);

    /**
     * 分页查询未被逻辑查询删除的一卡通信息数据
     *
     * @return
     */
    @Query(value = "select * from sys_card where is_deleted =false ", nativeQuery = true)
    Page<SysCard> getAllSysCard(Pageable pageable);

    /**
     * 查询一卡通数据通过学号
     * @param jobNumber
     * @return
     */
    @Query(value = "select * from first_smart_campus.sys_card where job_number = ?1 and is_deleted = false",nativeQuery = true)
    SysCard getSysCardByJobNumber(String jobNumber);

    /**
     * 通过卡号和卡密查找一卡通数据
     * @param cardNumber
     * @param cardPassword
     * @return
     */
    SysCard findSysCardByCardNumberAndCardPassword(String cardNumber,String cardPassword);

}