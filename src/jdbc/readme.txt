http://yangleiol.iteye.com/blog/731694        spring事务的实现

Spring 对DataSource进行事务管理的关键在于ConnectionHolder和TransactionSynchronizationManager。
  0.先从TransactionSynchronizationManager中尝试获取连接
  1.如果前一步失败则在每个线程上，对每个DataSouce只创建一个Connection
   2.这个Connection用ConnectionHolder包装起来，由TransactionSynchronizationManager管理
  3.再次请求同一个连接的时候，从TransactionSynchronizationManager返回已经创建的ConnectionHolder，然后调用ConnectionHolder的request将引用计数+1
  4.释放连接时要调用ConnectionHolder的released，将引用计数-1
  5.当事物完成后，将ConnectionHolder从TransactionSynchronizationManager中解除。当谁都不用，这个连接被close 