package cn.may.mybatis.transactionDemo;

import cn.may.mybatis.transactionDemo.Dao.JdbcTxManagerDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {

    @Test
    public void testNoTransaction() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cn/may/mybatis/transactionDemo/transactionDemo.xml");
        JdbcTxManagerDemo demo = (JdbcTxManagerDemo) context.getBean("jdbcDaoManager");
        //Tom 向 Marry 转账1000
        demo.updateUser("Tom", 100);
    }
}
