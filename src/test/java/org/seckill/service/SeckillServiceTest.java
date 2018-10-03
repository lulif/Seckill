package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetbyId() throws Exception {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

//	@Test
//	public void testExportSeckillUrl() throws Exception {
//		long id = 1000;
//		Exposer exposer = seckillService.exportSeckillUrl(id);
//		logger.info("exposer={}", exposer);
//	}
//
//	@Test
//	public void testExecuteSeckill() throws Exception {
//		long id = 1000;
//		long phone = 1230332;
//		String md5 = "adcffc3d885a591efebf50c8b26acce1";
//		try {
//			SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
//			logger.info("seckillExecution", seckillExecution);
//		} catch (RepeatKillException e1) {
//			logger.error(e1.getMessage());
//		} catch (SeckillCloseException e2) {
//			logger.error(e2.getMessage());
//		}
//	}
    //ºØ≥…≤‚ ‘
	@Test
	public void testSeckillLogic() throws Exception {
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if (exposer.isExposed()) {
			logger.info("exposer={}", exposer);
			long phone = 12312313;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
				logger.info("result={}", execution);
			} catch (RepeatKillException e1) {
				logger.error(e1.getMessage());
			} catch (SeckillCloseException e2) {
				logger.error(e2.getMessage());
			}
		} else {
//			System.out.println("1231231231313");
			logger.warn("exposer={}", exposer);
		}
	}
	
	@Test
	public void testexecuteSeckillProcedure()throws Exception{
		long seckillId=1000;
		long phone=144212412;
		String md5=null;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			 md5=exposer.getMd5();
			SeckillExecution execution=seckillService.executeSeckillProcedure(seckillId, phone, md5);
			System.out.println(123233333);
			logger.info(execution.getStateInfo());
		}
	}
}
