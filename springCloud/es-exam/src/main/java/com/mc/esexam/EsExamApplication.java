package com.mc.esexam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;


@OpenAPIDefinition(
        info = @Info(
                title="考试管理接口",
                description="对考试信息进行管理",
                version="version 1.0",
                termsOfService="www.baidu.com",
                contact=@Contact(
                        name="马超",
                        url="localhost：9998",
                        email="2563622159@qq.com"
                ),
                license=@License(
                        name="生产许可证",
                        url="localhost：8848"
                )
        ),
        servers = {
                @Server(url = "http://localhost:9080", description = "Local development server")
        }
)

@Slf4j
@EnableAsync
@RefreshScope
@EnableFeignClients
@SpringBootApplication
public class EsExamApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(EsExamApplication.class, args).getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application: '{}' is running Success! \n\t" +
                        "Local URL: \thttp://localhost:{}\n\t" +
                        "Document:\thttp://localhost:{}/doc.html\n" +
                        "Gateway Document:\thttp://localhost:9998/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.port"));
    }

}
