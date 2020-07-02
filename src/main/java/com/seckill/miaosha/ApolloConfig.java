package com.seckill.miaosha;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@Data
public class ApolloConfig {
    @Value("${secKillFlag}")
    private Boolean secKillFlag;
    @Value("${secKillStartTime}")
    private Boolean secKillStartTime;
    @Value("${secKillEndTime}")
    private Boolean secKillEndTime;
    @Value("${secKillPrice}")
    private Boolean secKillPrice;
    @Value("${secKillNum}")
    private Boolean secKillNum;
    @Value("${secKillProudeId}")
    private Boolean secKillProudeId;
}
