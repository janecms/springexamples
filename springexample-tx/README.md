## Spring 事务属性分析
```
public interface TransactionDefinition {
    /**
    传播属性
    */
	int getPropagationBehavior();
    /**
    隔离级别
    只有与PROPAGATION_REQUIRED或PROPAGATION_REQUIRES_NEW结合才有意义。
    */
    int getIsolationLevel();

	/**
    返回事务超时。
    必须返回几秒钟，或TIMEOUT_DEFAULT（-1不支持)。
    只有与PROPAGATION_REQUIRED或PROPAGATION_REQUIRES_NEW结合才有意义。
	 */
	int getTimeout();

	/**
	 返回是否优化为只读事务。
     只读标志适用于任何事务上下文，无论是由实际资源事务（PROPAGATION_REQUIRED / PROPAGATION_REQUIRES_NEW）
     支持还是在资源级别（PROPAGATION_SUPPORTS）处于非事务性操作。
	 */
	boolean isReadOnly();

	/**
        返回此交易的名称。 可以为null。
        这将用作事务监视器中显示的事务名称（如果适用）（例如WebLogic）。
        在Spring的声明性事务的情况下，公开的名称将是完全限定的类名+“”。 +方法名称（默认）。
	 */
	String getName();
}
```
### 事务隔离级别
 隔离级别是指若干个并发的事务之间的隔离程度。
- TransactionDefinition.ISOLATION_DEFAULT：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是TransactionDefinition.ISOLATION_READ_COMMITTED。

- TransactionDefinition.ISOLATION_READ_UNCOMMITTED：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。

- TransactionDefinition.ISOLATION_READ_COMMITTED：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。

- TransactionDefinition.ISOLATION_REPEATABLE_READ：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。

- TransactionDefinition.ISOLATION_SERIALIZABLE：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。



[常用数据库JDBC URL 格式](http://blog.csdn.net/ring0hx/article/details/6152528)

[Mysql的JDBC的常见连接属性](http://blog.csdn.net/legend_x/article/details/39024567)

[tutorialspoint](https://www.tutorialspoint.com/spring/spring_transaction_management.htm)

[全面分析 Spring 的编程式事务管理及声明式事务管理](https://www.ibm.com/developerworks/cn/education/opensource/os-cn-spring-trans/)

