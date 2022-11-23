package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	// main메소드는 Spring이 관리 안한다.
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired //자동으로 주입
	DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("스프링 부트가 관리하는 빈을 사용할 수 있다.");

		Connection conn = dataSource.getConnection();

		//JDBC프로그래밍
		PreparedStatement ps = conn.prepareStatement("select role_id, name from role");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int roleId = rs.getInt("role_id");
			String name = rs.getString("name");
			System.out.println(roleId + ", " + name);
		}
		rs.close();
		ps.close();
		conn.close();


	}
}



	//DataSource Bean(Spring이 관맇는 객체)

