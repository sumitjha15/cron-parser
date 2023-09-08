package com.cronparser.demo;

import com.cronparser.demo.parsers.ExpressionEvaluator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CronParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronParserApplication.class, args);
		String expression = "";
		//String expression = "*/5 2 1,15 * * /usr/bin/find";
		if(args.length==0||args[0].isEmpty()){
			System.out.println("Enter cron expression:\n");
			Scanner sc = new Scanner(System.in);
			expression = sc.nextLine();
		}else{
			expression = args[0];
		}
		if(expression.startsWith("\"")&&expression.endsWith("\"")){
			expression = expression.substring(1,expression.length());
		}
		ExpressionEvaluator evaluateExpression = new ExpressionEvaluator(expression);
		if(evaluateExpression.validate()){
			System.out.println(evaluateExpression.describe());
		}
		else{
			System.out.println("Failed to validate !!");
		}
	}

}
