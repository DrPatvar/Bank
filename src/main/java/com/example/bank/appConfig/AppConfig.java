package com.example.bank.appConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;

import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException{
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

}
