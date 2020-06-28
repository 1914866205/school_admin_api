package com.soft1851.smart.campus.utils;

import com.soft1851.smart.campus.model.entity.Clazz;
import com.soft1851.smart.campus.model.entity.Examination;
import com.soft1851.smart.campus.model.entity.SysUser;
import com.soft1851.smart.campus.model.vo.EntityVo;
import com.soft1851.smart.campus.model.vo.ExaminationVo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public class DataTypeChange<obj> {

    /**
     * object转换为map
     *
     * @param objects
     * @return
     */
    public static List<Map<String, Object>> objectToMap(Object[] objects) {
        if (objects == null) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Object obj : objects) {
            try {
                Map<String, Object> map = new LinkedHashMap<>();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    //设置私有属性为true
                    field.setAccessible(true);
                    //获取字段名和字段值
                    map.put(field.getName(), field.get(obj));
                }
                maps.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maps;
    }

    /**
     * 将 List<Object> 对象转为指定类型的 List 集合
     * <p>
     * 使用方法：
     * 1. 自己创建一个 Vo 对象，并实现 EntityVo 接口
     * 2. 在使用的地方调用方法即可，例如：
     * <p>
     * List<Object> examinationVos = examinationRepository.selectAll(pageDto);
     * List<EntityVo> voList = DataTypeChange.changeObj(examinationVos, ExaminationVo.class)
     * <p>
     * 注意：该方法返回值为 List<entity>，但是会根据自己给的第二个参数自动变更为自己的 Vo 对象
     *
     * @param list  被转换的 List<Object>对象
     * @param clazz 指定的实体类类型
     * @return
     */
    public static List<EntityVo> changeObj(List<Object> list, Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        List<EntityVo> data = new ArrayList<>(10);
        // 遍历数据集
        for (Object obj : list) {
            // 每一行数据都是一个数组。
            Object[] rowArray = (Object[]) obj;
            // 获取该类中的所有属性
            Field[] fields = clazz.getDeclaredFields();
            EntityVo entity = null;
            try {
                entity = (EntityVo) clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // 拿出属性名
                String fieldNam = field.getName();
                // 将首字母变为大写
                String fieldName = fieldNam.substring(0, 1).toUpperCase() + fieldNam.substring(1);
                // 获取该属性的类型值（包路径）
                String type = field.getGenericType().toString().substring(6);
                // 将该字符串类型的 type 转为 Java中的 Class
                Class<?> typeClass = null;
                try {
                    typeClass = Class.forName(type);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // 拼接属性名，组成一个方法，进行调用
                Method setMethod = null;
                try {
                    setMethod = clazz.getMethod(("set" + fieldName), typeClass);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                assert setMethod != null;
                try {
                    if (typeClass == String.class) {
                        String args = (String) rowArray[i];
                        setMethod.invoke(entity, args);
                    }
                    if (typeClass == Integer.class) {
                        Integer args = (Integer) rowArray[i];
                        setMethod.invoke(entity, args);
                    }
                    if (typeClass == Long.class) {
                        Long args = Long.valueOf(String.valueOf(rowArray[i]));
                        setMethod.invoke(entity, args);
                    }
                    if (typeClass == Double.class) {
                        Double args = (Double) rowArray[i];
                        setMethod.invoke(entity, args);
                    }
                    if (typeClass == Timestamp.class) {
                        Timestamp args = (Timestamp) rowArray[i];
                        setMethod.invoke(entity, args);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            data.add(entity);
        }
        return data;
    }
}


















