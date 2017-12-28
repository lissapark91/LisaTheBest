package kr.or.nextit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogTest {
	private static Logger logger = Logger.getLogger(LogTest.class);//자기 자신 클래스

	public static void main(String[] args) {
		
		// 로그 레벨
		// trace - debug - info - warning - error - fatal(심각)
		
		BasicConfigurator.configure(); // 기본 세팅
		
		logger.setLevel(Level.WARN); //warn이후 부터 출력 
		
		logger.debug("debug메세지 입니다.");
		logger.info("info메시지 입니다.");
		logger.warn("warning메세지 입니다.");
		logger.error("error메세지 입니다.");
		logger.fatal("fatal메세지 입니다.");
		
/*		기본세팅의 경우
 * 		0 [main] DEBUG kr.or.nextit.LogTest  - debug메세지 입니다.
		1 [main] INFO kr.or.nextit.LogTest  - info메시지 입니다.
		1 [main] WARN kr.or.nextit.LogTest  - warning메세지 입니다.
		1 [main] ERROR kr.or.nextit.LogTest  - error메세지 입니다.
		1 [main] FATAL kr.or.nextit.LogTest  - fatal메세지 입니다.
*/
		
		
	}
}
