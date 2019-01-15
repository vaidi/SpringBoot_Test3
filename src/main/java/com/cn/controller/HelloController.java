package com.cn.controller;

import com.cn.bo.ResultDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResultDO say(@RequestParam(name = "mobile",required = false) String mobile){
        ResultDO resultDO = new ResultDO();
        resultDO.setSuccess(true);
        return resultDO;
    }
}
