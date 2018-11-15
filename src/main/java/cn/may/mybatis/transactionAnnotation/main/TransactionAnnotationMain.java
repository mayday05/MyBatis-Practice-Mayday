package cn.may.mybatis.transactionAnnotation.main;

import cn.may.mybatis.transactionAnnotation.Dao.JdbcTemplateTxHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于注解的事务例子
 *
 * @author mayday
 * @date 2018-11-15
 */
public class TransactionAnnotationMain {

    /**
     * 事务注解方式测试1,事务方法不捕获异常，抛给上层（结果成功回滚）
     */
    @Test
    public void testTransactionAnnotation() {
        // 加载指定路径bean.xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cn/may/mybatis/transactionAnnotation/config/transactionAnnotation.xml");

        // 对于Spring AOP 采用两种代理方法，一种是常规JDK，一种是CGLIB，
        // JdbcTemplateTxManager实现了一个接口JdbcTemplateTxHandler
        // 当代理对象实现了至少一个接口时，默认使用JDK动态创建代理对象，
        // 当代理对象没有实现任何接口时，就会使用CGLIB方法。
        // 由于JdbcTemplateTxManager实现了JdbcTemplateTxHandler接口，所以强制转换必须用父类JdbcTemplateTxHandler来定义
        JdbcTemplateTxHandler handler = (JdbcTemplateTxHandler) context.getBean("jdbcTemplateTxManager");
        //Tom 向 Marry 转账1000
        handler.addUser("Tom3", 100);
    }

    /**
     * 事务注解方式测试2，事务捕获异常并向上抛运行时异常（结果成功回滚）
     */
    @Test
    public void testTransactionAnnotation2() {
        // 加载指定路径bean.xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cn/may/mybatis/transactionAnnotation/config/transactionAnnotation.xml");

        // 对于Spring AOP 采用两种代理方法，一种是常规JDK，一种是CGLIB，
        // JdbcTemplateTxManager实现了一个接口JdbcTemplateTxHandler
        // 当代理对象实现了至少一个接口时，默认使用JDK动态创建代理对象，
        // 当代理对象没有实现任何接口时，就会使用CGLIB方法。
        // 由于JdbcTemplateTxManager实现了JdbcTemplateTxHandler接口，所以强制转换必须用父类JdbcTemplateTxHandler来定义
        JdbcTemplateTxHandler handler = (JdbcTemplateTxHandler) context.getBean("jdbcTemplateTxManager");
        //Tom 向 Marry 转账1000
        handler.updateUser("Tom2", 200);
    }
}
