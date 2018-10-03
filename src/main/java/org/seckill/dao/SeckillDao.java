package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/*
	 * �����
	 * �������ͣ����¼�¼������
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);

	/*
	 * ����id��ѯ��ɱ����
	 */
	Seckill queryById(long seckillId);

	/*
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * javaû�б����βμ�¼���βλ���arg0,arg1,һ���������ã��������Ҫ����mybatis�ĸ�����ʲôλ��
	 * @Param("offset")��@Param("offset")��������mybatis����Ĳ�������
	 */
	List<Seckill> queryAll(@Param("offet")int offet,@Param("limit") int limit);
	
	void killByProcedure(Map<String,Object>paramMap);

}
