package com.curtain.messagechat.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import sun.misc.Version;

/**
 * 自定义健康端点 接口方式
 *
 * @author Curtain
 * @date 2018/11/27 14:22
 */

@Component("my1")
public class MyHealthIndicatorOne implements HealthIndicator {
    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = check();
        if (code != 0) {
            Health.down().withDetail("code", code).withDetail("version", VERSION).up().build();
        }
        return Health.up().withDetail("code", code).withDetail("version", VERSION).up().build();
    }

    private int check() {
        return 0;
    }
}
