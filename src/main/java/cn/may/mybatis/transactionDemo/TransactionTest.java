package cn.may.mybatis.transactionDemo;

import cn.may.mybatis.transactionDemo.Dao.JdbcTxManagerDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于编程式事务范例
 *
 * @author mayday
 * @date 2018-11-14
 */
public class TransactionTest {

    @Test
    public void testNoTransaction() {
        // 加载指定路径bean.xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cn/may/mybatis/transactionDemo/transactionDemo.xml");
        JdbcTxManagerDemo demo = (JdbcTxManagerDemo) context.getBean("jdbcDaoManager");
        //Tom 向 Marry 转账1000
        demo.updateUser("Tom", 100);
    }
}
