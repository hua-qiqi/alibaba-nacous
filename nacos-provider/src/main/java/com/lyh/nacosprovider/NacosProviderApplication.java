package com.lyh.nacosprovider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@RestController
public class NacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }

    @Value("${age}")
    private String age;
    @Autowired
    private SentinelService sentinelService;
    // 注解支持的配置Bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

        @SentinelResource(value = "doSomeThing")
        @GetMapping("/hello")
        public String hello(@RequestParam String name) {
            return "hello " + name;
        }

    @GetMapping("/age")
    public String age() {
        return "hello I am"+age+"years old";
    }




    @GetMapping("/sentinel")
    public String sentinel() {
        return sentinelService.doSomeThing("hello " + new Date());
    }
}
