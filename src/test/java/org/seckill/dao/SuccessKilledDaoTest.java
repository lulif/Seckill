package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//junit����ʱ����springioc
@RunWith(SpringJUnit4ClassRunner.class)
// ����spring������ʱ��Ӧ��spring-dao.xml
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessKilledDaoTest {

	// ע��Daoʵ������
	// springIoc�в���seckillDao��ʵ����
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		long phone = 13175115943L;
		System.out.println(successKilledDao.insertSuccessKilled(1001, phone));

	}

	@Test
	public void testQueryByIdWithSeckill() {
		long phone = 13175115943L;
		SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(1001, phone);
	    System.out.println(successKilled);
	    System.out.println(successKilled.getSeckillId());
		

	}
}
