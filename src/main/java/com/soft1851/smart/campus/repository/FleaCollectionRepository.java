
package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.dto.CancelCollectionDto;
import com.soft1851.smart.campus.model.vo.CollectionVo;
import com.soft1851.smart.campus.model.entity.FleaCollection;
import com.soft1851.smart.campus.model.dto.CancelCollectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName a.java
 * @Description TODO
 * @createTime 2020年06月09日 11:26:00
 */
public interface FleaCollectionRepository extends JpaRepository<FleaCollection, Long> {
    @Query(value = "select new com.soft1851.smart.campus.model.vo.CollectionVo(g.goodsName,g.goodsDescription,g.goodsPrice,g.goodsImgUrl,g.goodsMark,u.username,u.phoneNumber,c.createTime)" +
            "from FleaCollection c " +
            "left join c.fleaGoods g " +
            "left join c.fleaUser u")
    List<CollectionVo> getCollection();

    /**
     * 根据用户ID以及商品ID逻辑删除某个收藏
     *
     * @param collectionDto
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update FleaCollection set isDeleted = true where fleaGoods.pkFleaGoodsId = :#{#collectionDto.getGoodsId()} and fleaUser.pkFleaUserId = :#{#collectionDto.getUserId()}")
    int logicalDel(CancelCollectionDto collectionDto);
}
