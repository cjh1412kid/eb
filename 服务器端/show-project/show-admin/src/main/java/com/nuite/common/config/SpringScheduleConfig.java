package com.nuite.common.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
//定时任务调用一个线程池中的线程。
public class SpringScheduleConfig implements SchedulingConfigurer {
  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
      //参数传入一个size为10的线程池
      scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
  }
}
