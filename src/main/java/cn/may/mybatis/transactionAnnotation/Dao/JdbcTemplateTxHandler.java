package cn.may.mybatis.transactionAnnotation.Dao;


public interface JdbcTemplateTxHandler {

    void addUser(String name, int age);

    void updateUser(String name, int age);
}
