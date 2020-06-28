
package com.soft1851.smart.campus.repository;

import com.soft1851.smart.campus.model.entity.FleaGoods;
import com.soft1851.smart.campus.model.vo.FleaGoodsVo;
import com.soft1851.smart.campus.model.vo.GoodsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsRepository.java
 * @Description TODO
 * @createTime 2020年06月09日 13:53:00
 */
public interface FleaGoodsRepository extends JpaRepository<FleaGoods, Long> {

    /**
     * 根据搜索内容进行模糊查询
     *
     * @param goodsName String
     * @param goodsDescription String
     * @return List<FleaGoods>
     */
    List<FleaGoods> findFleaGoodsByGoodsNameLikeOrGoodsDescriptionLike(String goodsName, String goodsDescription);

    /**
     * 分页查询所有商品
     *
     * @param pageable Pageable
     * @return List<GoodsVo>
     */
    @Query(value = "select new com.soft1851.smart.campus.model.vo.FleaGoodsVo(g.pkFleaGoodsId,g.goodsName,g.goodsDescription,g.goodsImgUrl,g.goodsPrice,g.goodsMark," +
            "g.goodsCreateTime,t.pkFleaTypeId,t.typeName,u.pkFleaUserId,u.nickname,u.username,g.isDeleted) " +
            "from FleaGoods g " +
            "left join g.fleaType t " +
            "left join g.fleaUser u ")
    Page<FleaGoodsVo> getAllGoodsByTime(Pageable pageable);

    /**
     * 根据商品id查询指定商品的详细信息
     *
     * @param goodId Long
     * @return List<FleaGoodsVo>
     */
    @Query(value = "select new com.soft1851.smart.campus.model.vo.FleaGoodsVo(g.pkFleaGoodsId,g.goodsName,g.goodsDescription,g.goodsImgUrl,g.goodsPrice,g.goodsMark," +
            "g.goodsCreateTime,t.pkFleaTypeId,t.typeName,u.pkFleaUserId,u.nickname,u.username,g.isDeleted) " +
            "from FleaGoods g " +
            "left join g.fleaType t " +
            "left join g.fleaUser u " +
            "where g.pkFleaGoodsId = ?1 ")
    List<FleaGoodsVo> selectGoodsById(Long goodId);

    /**
     * 下架商品
     *
     * @param goodId Long
     * @return int
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "update FleaGoods set isDeleted = 1 where pkFleaGoodsId = ?1 ")
    int soldOutGood(Long goodId);

    /**
     * 批量逻辑删除
     *
     * @param batchId List<String>
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update FleaGoods set isDeleted = 1 where pkFleaGoodsId in (:batchId)")
    int batchLogicalDel(List<Long> batchId);
    /**
     * 查询top前五的标签
     *
     * @return List<MarkVo>
     */
    @Query(value = "select t.goodsMark from (select goods_mark as goodsMark,count(*) as count from flea_goods GROUP BY goodsMark) t order by count desc limit 5", nativeQuery = true)
    List<String> selectTopFiveMark();
}
