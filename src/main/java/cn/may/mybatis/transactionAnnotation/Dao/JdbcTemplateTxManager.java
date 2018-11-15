package cn.may.mybatis.transactionAnnotation.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class JdbcTemplateTxManager implements JdbcTemplateTxHandler {

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    /**
     * 添加用户
     *
     * @param name
     * @param age
     */
    @Transactional
    @Override
    public void addUser(String name, int age) {
        /**
         * 注意：
         * 1、 @Transactional 只能被应用到public方法上, 对于其它非public的方法,如果标记了@Transactional也不会报错,但方法没有事务功能
         * 2、 在事物中try catch但未抛出异常，导致事务未回滚（就是下列注释导致事务没有回滚）
         */
        //try {
        String sql = "insert into users(name, age) values(?, ?)";
        System.out.println("======================================SQL============================" + sql);
        jdbcTemplate.update(sql, name, age);
        int i = 1 / 0;
        System.out.println("========================事务提交===========================================");
        //} catch (Exception e) {
        //   System.out.println("========================异常开始回滚Exception=====================" + e);
        //}
    }

    /**
     * 更新用户
     *
     * @param name
     * @param age
     */
    @Transactional
    @Override
    public void updateUser(String name, int age) {
        /**
         * 注意：
         * 1、aSpring AOP 默认对RuntimeException()异常或是其子类进行事务回滚，也就是说
         * 事务回滚：throw new RuntimeException("xxxxxxxxxxxx"); 
         * 事物不回滚：throw new Exception("xxxxxxxxxxxx"); 
         */
        try {
            String sql = "update users set age = ? where name = ?";
            System.out.println("======================================SQL============================" + sql);
            jdbcTemplate.update(sql, age, age);
            int i = 1 / 0;
            System.out.println("========================事务提交===========================================");
        } catch (Exception e) {
            System.out.println("========================异常开始回滚Exception=====================" + e);
            throw new RuntimeException("Exception");
        }

        /**
         * 如何在抛出Exception或者自定义异常时，事务也进行回滚呢？有以下几种方案：
         * 1、在捕获到异常或者显示的抛出 RuntimeException
         *
         * 2、手动回滚----在捕获到异常或者显示的抛出自定义异常前，加一段代码：
         *
         * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         *
         * 3、在AOP的配置文件中，添加如下信息：
         *
         * <tx:method name="add*" propagation="REQUIRED" rollback-for="com.xxx.xxx.xxxException"/>
         *
         *       这样，在抛出自定义异常时，就会进行事务的回滚了         *
         * 小结：
         *
         * 方法1：在做测试时比较方便，放在业务中无法区分具体的错误信息
         *
         * 方法2：在个别业务处理或者调试代码时使用，放在代码中不方便维护，每个异常都需要增加。
         *
         * 方法3：适合业务开发，也符合AOP的理念。将事务控制和业务分离
         */
    }


}

