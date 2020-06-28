package com.soft1851.smart.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft1851.smart.campus.model.dto.PageDto;
import com.soft1851.smart.campus.model.entity.UserAccount;
import com.soft1851.smart.campus.model.vo.StudentVo;
import com.soft1851.smart.campus.model.vo.TeacherVo;
import com.soft1851.smart.campus.model.vo.UserAccountVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Tao
 * @version 1.0
 * @ClassName UserAccountMapper
 * @Description TODO
 * @date 2020-06-14 13:03
 **/
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    /**
     * 分页查询所有学生数据
     *
     * @param pageDto
     * @return
     */
    @Select("SELECT u.pk_user_account_id,u.job_number, u.gender,u.gmt_create,u.is_deleted,u.job_number,u.phone_number," +
            "u.status,u.user_name,c.name " +
            "FROM user_account u " +
            "LEFT JOIN clazz c " +
            "ON u.clazz_id = c.pk_clazz_id " +
            "WHERE u.role = 1 AND u.is_deleted = false " +
            "ORDER BY gmt_create DESC " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize} ")
    List<UserAccountVo> getUserAccountVo(@Param("pageDto") PageDto pageDto);

    /**
     * 获取所有学生
     * @return
     */
    @Select("SELECT job_number, user_name, pk_user_account_id FROM user_account WHERE role = 1 AND is_deleted = false AND clazz_id = 0 ")
    List<Map<String, Object>> getAllStudents();
    /**
     * 分页查询所有教师数据
     *
     * @param pageDto
     * @return
     */
    @Select("SELECT u.pk_user_account_id,u.gender,u.gmt_create,u.is_deleted,u.job_number,u.phone_number," +
            "u.status,u.user_name,c.name " +
            "FROM user_account u " +
            "LEFT JOIN clazz c " +
            "ON u.clazz_id = c.pk_clazz_id " +
            "WHERE u.role = 2 AND u.is_deleted = false " +
            "ORDER BY gmt_create DESC " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize}")
    List<UserAccountVo> getTeacherUserAccountVo(@Param("pageDto") PageDto pageDto);

    /**
     * 通过教工号查询用户数据
     *
     * @param jobNumber
     * @return
     */
    @Select("SELECT * FROM user_account WHERE job_number = #{jobNumber} AND is_deleted = false")
    UserAccount getUserAccountByJobNumber(String jobNumber);

    /**
     * 通过id修改用户账号信息
     * @param userAccount
     * @return
     */
    @Update("UPDATE user_account SET gender=#{userAccount.gender}, job_number=#{userAccount.jobNumber}, " +
            "phone_number=#{userAccount.phoneNumber}, user_name=#{userAccount.userName}, role=#{userAccount.role} " +
            "WHERE pk_user_account_id=#{userAccount.pkUserAccountId} ")
    int updateUserAccountById(@Param("userAccount") UserAccount userAccount);

    /**
     * 查找所有教师数据
     *
     * @return
     */
    @Select("SELECT t.pk_user_account_id,t.job_number,t.user_name FROM user_account t WHERE t.is_deleted = false AND t.role = 2 ORDER BY gmt_create DESC ")
    List<TeacherVo> getAllTeacher();

    /**
     * 查询所有教师的所有信息
     * @return
     */
    @Select("SELECT t.* FROM user_account t WHERE t.is_deleted = false AND t.role = 2 ORDER BY gmt_create DESC ")
    List<UserAccount> getAllTeacherInfo();

    /**
     * 查询所有学生的所有信息
     * @return
     */
    @Select("SELECT t.* FROM user_account t WHERE t.is_deleted = false AND t.role = 1 ORDER BY gmt_create DESC ")
    List<UserAccount> getAllStudentInfo();

    /**
     * 查询未被分配的学生
     *
     * @param pageDto
     * @return
     */
    @Select("SELECT t.pk_user_account_id,t.job_number,t.user_name " +
            "FROM user_account t " +
            "WHERE t.is_deleted = false AND t.role = 1 AND clazz_id = 0 " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize}")
    List<StudentVo> getAllUndistributedStudents(@Param("pageDto") PageDto pageDto);

    /**
     * 模糊查询学生（用于添加学生到某个班级中）
     *
     * @param keyword
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "SELECT pk_user_account_id,job_number,user_name FROM user_account ",
            "WHERE 1=1 ",
            "<when test='keywords!=null'> ",
            "AND user_name LIKE CONCAT('%',#{keywords},'%') ",
            "OR job_number LIKE CONCAT('%',#{keywords},'%') ",
            "AND role = 1 ",
            "</when> ",
            "</script>"})
    List<StudentVo> findStudentLike(String keyword) throws SQLException;

    /**
     * 模糊查询教师
     *
     * @param keyword
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "SELECT job_number,user_name FROM user_account ",
            "WHERE 1=1 ",
            "<when test='keywords!=null'> ",
            "AND user_name LIKE CONCAT('%',#{keywords},'%') ",
            "OR job_number LIKE CONCAT('%',#{keywords},'%') ",
            "AND role = 2 ",
            "</when> ",
            "</script>"})
    List<TeacherVo> findTeacherLike(String keyword) throws SQLException;


    /**
     * 查询某个班级的所有学生id
     *
     * @param clazzId
     * @return
     * @throws SQLException
     */
    @Select("SELECT pk_user_account_id, job_number, user_name FROM user_account WHERE clazz_id=#{clazzId} AND is_deleted = false")
    List<Map<String, Object>> findStudentsByClazzId(Long clazzId) throws SQLException;

    /**
     * 查询某个班级的所有学生学号
     *
     * @param clazzId
     * @return
     * @throws SQLException
     */
    @Select("SELECT pk_user_account_id FROM user_account WHERE clazz_id=#{clazzId} AND is_deleted = false")
    List<String> findUserIdByClazzId(Long clazzId) throws SQLException;

    /**
     * 根据班级id查询用户教工号
     * @param clazzId
     * @return
     * @throws SQLException
     */
    @Select("SELECT job_number FROM user_account WHERE clazz_id=#{clazzId} AND is_deleted=false AND role=1")
    List<String> findJobNumberByClazzId(Long clazzId) throws SQLException;

}
