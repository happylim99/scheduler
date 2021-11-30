package com.sean.scheduler

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages= ["com.sean"])
//@EnableAutoConfiguration(exclude = [
//	DataSourceAutoConfiguration::class,
//	DataSourceTransactionManagerAutoConfiguration::class,
//	HibernateJpaAutoConfiguration::class])
class SchedulerApplication

fun main(args: Array<String>) {
	runApplication<SchedulerApplication>(*args)
}
