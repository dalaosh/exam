package com.mc.esgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Component
public class GlobalFilterConfig implements GlobalFilter, Ordered {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String HEADER_NAME = "token";
    private static final int daysTime=60*60*24;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("============过滤器============");
        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 获取请求地址
        String url = request.getURI().getPath();
        // 获取token信息
        String token = request.getHeaders().getFirst(HEADER_NAME);
        // 判断是否为白名单请求，以及一些内置不需要验证的请求。(登录请求也包含其中)。
        // 如果当前请求中包含token令牌不为空的时候，也会继续验证Token的合法性，这样就能保证
        // Token中的用户信息被业务接口正常访问到了。而如果当token为空的时候，白名单的接口可以
        // 被网关直接转发，无需登录验证。当然被转发的接口，也无法获取到用户的token身份数据了。
//        System.out.println("============开始============");
        if (this.shouldNotFilter(url)) {
            return chain.filter(exchange);
        }
//        System.out.println("============完成1============");
        //验证token是否为空
        if (StringUtils.isEmpty(token)) {
            return unAuthorize(exchange);
        }
//        System.out.println("============完成2============");

        String[] tok= new String[0];
        if (token != null) {
            tok = token.split(",");
        }
        String token1=tok[0];
        String token2=tok[1];
        //验证redis中是否存在token
        if(!redisTemplate.hasKey(token1)){
            return unAuthorize(exchange);
        }
        //验证通过，刷新token过期时间
        redisTemplate.expire(token1, daysTime,TimeUnit.SECONDS);
        String userId = String.valueOf(redisTemplate.opsForValue().get(token1));
        redisTemplate.expire(token2, daysTime,TimeUnit.SECONDS);
        String src=String.valueOf(redisTemplate.opsForValue().get(token2));
        File folder = new File(src);
        boolean isEmpty = isFolderEmpty(folder);//空为true
        if(isEmpty){
            return unAuthorize(exchange);
        }
        System.out.println("============登录用户id："+userId+"============");
        //把新的 exchange放回到过滤链
        ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, token).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        System.out.println();
        return chain.filter(newExchange);
    }

    public static boolean isFolderEmpty(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            return files == null || files.length == 0;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // 返回未登录的自定义错误
    private Mono<Void> unAuthorize(ServerWebExchange exchange) {
        // 设置错误状态码为401
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 设置返回的信息为JSON类型
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 自定义错误信息
        String errorMsg = "{\"error\": \"" + "用户未登录或登录超时,请重新登录" +"\","+ "\"code\": \"401\"}";
//        String errorMsg = "{\"error\": \"" + "用户未登录或登录超时,请重新登录" + "\"}";
        // 将自定义错误响应写入响应体
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMsg.getBytes())));
    }


    /**
     * 判断当前请求URL是否为白名单地址，以及一些内置的不用登录的接口，
     *
     * @param url 请求的url。
     * @return 是返回true，否返回false。
     */
    private boolean shouldNotFilter(String url) {
        return     url.startsWith("/user/login")//登录
                || url.startsWith("/user/findMessage")
                || url.startsWith("/user/login/captcha")//验证码
                || url.startsWith("/user/forget/email")//邮箱验证码
                || url.startsWith("/user/forget/verify")//邮箱验证
                || url.startsWith("/user/forget")
                || url.startsWith("/user/excel")

                || url.startsWith("/user/admin/export")//导出excel
                || url.startsWith("/user/admin/upload")
                || url.startsWith("/user/student/export")//导出excel
                || url.startsWith("/user/student/upload")
                || url.startsWith("/user/teacher/export")//导出excel
                || url.startsWith("/user/teacher/upload")
                || url.startsWith("/user/caInstitution/export")//导出excel
                || url.startsWith("/user/caInstitution/upload")

                || url.startsWith("/exam/questionCode/export")//导出excel
                || url.startsWith("/exam/questionCode/upload")
                || url.startsWith("/exam/questionFill/export")//导出excel
                || url.startsWith("/exam/questionFill/upload")
                || url.startsWith("/exam/questionJudge/export")//导出excel
                || url.startsWith("/exam/questionJudge/upload")
                || url.startsWith("/exam/questionMulti/export")//导出excel
                || url.startsWith("/exam/questionMulti/upload")

                || url.startsWith("/es-user/v3/api-docs") //接口配置
                || url.startsWith("/es-mail/v3/api-docs")
                || url.startsWith("/es-info/v3/api-docs")
                || url.startsWith("/es-exam/v3/api-docs")
                || url.startsWith("/es-files/v3/api-docs")
                || url.startsWith("/es-statistic/v3/api-docs")
                || url.startsWith("/es-trustSeal/v3/api-docs")
                || url.startsWith("/es-websocket/v3/api-docs")
                || url.startsWith("/files/wang/") //文件上传与下载
                || url.startsWith("/files/avatar/")
                || url.startsWith("/files/course/")
                || url.startsWith("/files/md/")
                || url.startsWith("/files/realFace/")
                || url.startsWith("/files/testFace/")
                || url.startsWith("/files/idPhoto/")
                || url.startsWith("/statistic/")
                || url.startsWith("/websocket/")
                || !url.startsWith("/statistic/universityCollegeMajor/");
    }
}
