package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.FleaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaUserRepository.java
 * @Description TODO
 * @createTime 2020年06月09日 13:54:00
 */
public interface FleaUserRepository extends JpaRepository<FleaUser, Long> {
    List<FleaUser> findFleaUsersByNicknameLikeOrUsernameLikeOrPhoneNumberLikeOrSexLikeOrJobNumberLike(String nickname, String username, String phoneNumber, String sex, String jobNumber);
}

