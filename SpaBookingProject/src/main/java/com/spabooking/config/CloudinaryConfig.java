package com.spabooking.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	@Bean
	public Cloudinary getCloudinary() {
		Map config = new HashMap();
		config.put("cloud_name", "thuyen2k1");
		config.put("api_key", "241914695893819");
		config.put("api_secret", "aYdwQdH0tfkubmf1FFavjfp3PFU");
		config.put("secure", true);
		return new Cloudinary(config);
	}
}
