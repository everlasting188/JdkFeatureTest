http://yangleiol.iteye.com/blog/731694        spring�����ʵ��

Spring ��DataSource�����������Ĺؼ�����ConnectionHolder��TransactionSynchronizationManager��
  0.�ȴ�TransactionSynchronizationManager�г��Ի�ȡ����
  1.���ǰһ��ʧ������ÿ���߳��ϣ���ÿ��DataSouceֻ����һ��Connection
   2.���Connection��ConnectionHolder��װ��������TransactionSynchronizationManager����
  3.�ٴ�����ͬһ�����ӵ�ʱ�򣬴�TransactionSynchronizationManager�����Ѿ�������ConnectionHolder��Ȼ�����ConnectionHolder��request�����ü���+1
  4.�ͷ�����ʱҪ����ConnectionHolder��released�������ü���-1
  5.��������ɺ󣬽�ConnectionHolder��TransactionSynchronizationManager�н������˭�����ã�������ӱ�close 