package org.seckill.exception;
/*
 * �ظ���ɱ�쳣���������쳣��
 * spring����ʽ�������ֻ�����������쳣�ع����ԣ��׳����������쳣����ع���
 */
public class RepeatKillException extends SeckillException{

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RepeatKillException(String message) {
		super(message);
	
	}
	
	

}
