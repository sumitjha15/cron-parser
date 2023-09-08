package com.cronparser.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CronParserApplicationTests {

	@Test
	void testMainWithValidCronExpression() {
		CronParserApplication.main(new String[] {"*/5 2 1,15 * * /usr/bin/find"});
	}

	@Test
	void testMainWithInValidCronExpressionForMinute(){
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CronParserApplication.main(new String[] {"*/78 2 1,15 * * /usr/bin/find"});
		});
		assertEquals("Value out of range for field MINUTE",exception.getMessage());
	}

	@Test
	void testMainWithInValidCronExpressionForHour(){
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CronParserApplication.main(new String[] {"*/5 25 1,15 * * /usr/bin/find"});
		});
		assertEquals("Value out of range for field HOUR",exception.getMessage());
	}

	@Test
	void testMainWithInValidCronExpressionForDayOfMonth(){
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CronParserApplication.main(new String[] {"*/5 2 1,15,35 * * /usr/bin/find"});
		});
		assertEquals("Value out of range for field DAY_OF_MONTH",exception.getMessage());
	}

	@Test
	void testMainWithInValidCronExpressionForMonth(){
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CronParserApplication.main(new String[] {"*/5 2 1,15 13 * /usr/bin/find"});
		});
		assertEquals("Value out of range for field MONTH",exception.getMessage());
	}

	@Test
	void testMainWithInValidCronExpressionForDayOfWeek(){
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CronParserApplication.main(new String[] {"*/5 2 1,15 * 8 /usr/bin/find"});
		});
		assertEquals("Value out of range for field DAY_OF_WEEK",exception.getMessage());
	}



}
