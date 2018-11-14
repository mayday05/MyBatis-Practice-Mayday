package cn.may.mybatis.demo2.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyBatisUtil {


    public static SqlSession getSqlSession() {
        SqlSession session = null;
        try {
            //mybatis的配置文件
            File file = new File("src/main/java/cn/may/mybatis/demo2/mybatis-conf.xml");
            //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream in = new FileInputStream(file);

            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
            //Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //创建能执行映射文件中sql的sqlSession
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println("Exception = " + e);
        }
        return session;
    }
}
