package com.binbinxiu.ig;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author wzd
 */
@EnableSwagger2
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(
        basePackages = {"com.binbinxiu"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE)
        })
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
public class TaskApp {

    private static final Logger logger = LoggerFactory.getLogger(TaskApp.class);

    public static void main(String[] args) {
        logger.info("############开始启动task服务###########################");
        SpringApplication.run(TaskApp.class, args);
        logger.info("############启动task服务成功###########################");
    }

}
