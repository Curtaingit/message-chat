package com.curtain.messagechat.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义端点
 *
 * @Endpoint 是构建 rest 的唯一路径
 * 不同请求的操作，调用时缺少必需参数，或者使用无法转换为所需类型的参数，则不会调用操作方法，响应状态将为400（错误请求）
 * @ReadOperation = GET 响应状态为 200 如果没有返回值响应 404（资源未找到）
 * @WriteOperation = POST 响应状态为 200 如果没有返回值响应 204（无响应内容）
 * @DeleteOperation = DELETE 响应状态为 200 如果没有返回值响应 204（无响应内容）
 *
 * @author Curtain
 * @date 2018/11/27 14:31
 */

@Endpoint(id = "curtain")
public class MyEndPoint {

    @ReadOperation
    public Map<String,String> hello(){
        Map<String,String> result = new HashMap<>(8);
        result.put("author","Curtain");
        result.put("age","22");
        result.put("email","1158380326@qq.com");
        return result;
    }
}
