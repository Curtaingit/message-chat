package com.curtain.messagechat.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义健康端点 抽象类方式
 *
 * @author Curtain
 * @date 2018/11/27 14:26
 */

@Component("my2")
public class MyHealthIndicatorTwo extends AbstractHealthIndicator {

    private static final String VERSION = "v1.0.0";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = check();
        if (code != 0) {
            builder.down().withDetail("code", code).withDetail("version", VERSION).up().build();
        }
        builder.up().withDetail("code", code).withDetail("version", VERSION).up().build();

    }

    private int check() {
        return 0;
    }
}
