package org.seckill.exception;
/*
 * 重复秒杀异常（运行期异常）
 * spring声明式事务管理只接受运行期异常回滚策略，抛出非运行期异常不会回滚的
 */
public class RepeatKillException extends SeckillException{

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RepeatKillException(String message) {
		super(message);
	
	}
	
	

}
