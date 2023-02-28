package com.example.demo.resources;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public Path filePath() {
		return Paths.get("C:/dev/files/vue/boardfile");
	}
}
