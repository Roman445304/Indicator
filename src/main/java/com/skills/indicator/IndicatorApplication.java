package com.skills.indicator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


@SpringBootApplication
public class IndicatorApplication {



	public static void main(String[] args) {
		SpringApplication.run(IndicatorApplication.class, args);
	}



}
