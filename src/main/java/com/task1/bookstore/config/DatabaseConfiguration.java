package com.task1.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.task1.bookstore.repository")
public class DatabaseConfiguration {}
