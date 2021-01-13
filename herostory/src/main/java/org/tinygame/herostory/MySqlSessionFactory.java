package org.tinygame.herostory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * MySql会话工厂
 */
public final class MySqlSessionFactory {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlSessionFactory.class);

    /**
     * sql会话工厂对象
     */
    private static SqlSessionFactory _sqlSessionFactory;

    /**
     * 私有化构造器
     */
    private MySqlSessionFactory(){}

    /**
     * 初始化
     */
    public static void init(){
        try {
            _sqlSessionFactory = (new SqlSessionFactoryBuilder()).build(
                    Resources.getResourceAsStream("MyBatisConfig.xml")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启mysql会话
     * @return mysql 会话
     */
    public static SqlSession openSession(){
        if (null == _sqlSessionFactory){
            LOGGER.error("_sqlSessionFactory 尚未初始化");
            throw new RuntimeException("_sqlSessionFactory 尚未初始化");
        }

        return _sqlSessionFactory.openSession(true);
    }
}
