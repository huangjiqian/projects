package org.ormtest.step020.entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * 用户实体助手类
 */
public class UserEntity_Helper {
    /**
     * 将数据集装换为实体对象
     *
     * @param rs 数据集
     * @return
     * @throws Exception
     */
    public UserEntity create(ResultSet rs) throws Exception {
        if (null == rs) {
            return null;
        }
        //
        // 有了反射,
        // 这下就不怕实体类的修改了...
        // 实体类你随便改!
        // 我们还能再优化一步, 将这个 UserEntity_Handler 改的更通用!
        // 跳到 XxxEntity_Helper 类看看!
        //
        // 创建新的实体对象
        UserEntity ue = new UserEntity();

        Field[] fieldArray = ue.getClass().getFields();
        for (Field field : fieldArray){
            Column annoColumn = field.getAnnotation(Column.class);

            if (null == annoColumn) {
                continue;
            }

            String colName = annoColumn.name();

            Object colVal = rs.getObject(colName);

            if (null == colVal) {
                continue;
            }

            field.set(ue,colVal);

        }

        return ue;
    }
}
