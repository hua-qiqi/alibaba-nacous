package com.lyh.nacosprovider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {

    private Logger log= LoggerFactory.getLogger(SentinelService.class);

    @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler")
    public String doSomeThing(String str) {
        return str;
    }

    // 限流与阻塞处理
    public String exceptionHandler(String str, BlockException ex) {
        return "blockHandler：" + str;
    }

    // 熔断与降级处理
    @SentinelResource(value = "doSomeThing2", fallback = "fallbackHandler")
    public void doSomeThing2(String str) {
        log.info(str);
        throw new RuntimeException("发生异常");
    }

    public void fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
    }
}
