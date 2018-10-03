package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//junit����ʱ����springioc
@RunWith(SpringJUnit4ClassRunner.class)
// ����spring������ʱ��Ӧ��spring-dao.xml
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {
	// ע��Daoʵ������
	// springIoc�в���seckillDao��ʵ����
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() throws Exception {

		System.out.println(seckillDao.reduceNumber(1000, new Date()));

	}

	@Test
	public void testQueryById() throws Exception {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() throws Exception {
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}

	}

}
