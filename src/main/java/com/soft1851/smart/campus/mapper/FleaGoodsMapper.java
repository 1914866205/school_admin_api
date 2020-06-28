package com.soft1851.smart.campus.mapper;

import com.soft1851.smart.campus.model.vo.ShopVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 倪涛涛
 * @version 1.0.0
 * @ClassName FleaGoodsMapper.java
 * @Description TODO
 * @createTime 2020年06月26日 09:04:00
 */
public interface FleaGoodsMapper {

    List<ShopVo> selectTypeIdAndNumber();

    String findTypeNameById(String typeId);

    Double getAllProfit();
}
