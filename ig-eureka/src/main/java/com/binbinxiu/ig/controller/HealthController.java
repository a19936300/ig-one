package com.binbinxiu.ig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查接口
 * @Author wzd
 */
@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping("test")
    public String test() {
        return "success";
    }

}
