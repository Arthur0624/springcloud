package com.lyc.controller;

import com.lyc.entities.CommonResult;
import com.lyc.entities.Payment;
import com.lyc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult crate(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果:"+result);

        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(404, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功"+serverPort, payment);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
    }
}
