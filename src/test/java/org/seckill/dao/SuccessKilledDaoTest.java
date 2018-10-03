package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//junit启动时加载springioc
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring容器的时，应用spring-dao.xml
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessKilledDaoTest {

	// 注入Dao实现依赖
	// springIoc中查找seckillDao的实现类
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
