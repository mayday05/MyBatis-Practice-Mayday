package cn.may.mybatis.transactionDemo.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

public class JdbcTxManagerDemo {

    private DataSourceTransactionManager dataSourceTransactionManager;

    private JdbcTemplate jdbcTemplate;

    private TransactionTemplate transactionTemplate;

    public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据用户名减少账户金额
     */
    public void updateUser(String name, int age) {
        // 开启一个事务
        DefaultTransactionDefinition transDef = new DefaultTransactionDefinition(); // 定义事务属性
        // 可以不设置
        transDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED); // 设置传播行为属性
        // 获取新建事务的状态，相当于事务开始
        TransactionStatus status = dataSourceTransactionManager.getTransaction(transDef); // 获得事务状态
        try {
            String sql = "update users set age = ? where name = ?";
            System.out.println("======================================SQL============================" + sql);
            jdbcTemplate.update(sql, age, name);
            int i = 1 / 0;

            dataSourceTransactionManager.commit(status);// 提交
            System.out.println("==============事务提交===========================================");

        } catch (Exception e) {
            System.out.println("==================================异常开始回滚Exception=====================" + e);
            dataSourceTransactionManager.rollback(status);
        }


    }

}
