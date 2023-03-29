package com.binbinxiu.ig.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowableController {

    @Autowired
    private StartProcessInstance startProcessInstance;


    public void startProcessInstance() {
        startProcessInstance.startProcessInstance();
    }

    @GetMapping("/reqeustHoliday/{employeeName}/{numberOfDays}")
    public void reqeustHoliday(@PathVariable("employeeName") String employeeName, @PathVariable("numberOfDays") Integer numberOfDays) {
        startProcessInstance.submitHolidayRequest(employeeName, numberOfDays);
    }

    @GetMapping("/approveHoliday/{taskId}/{approved}/{remarks}")
    public void approveHoliday(@PathVariable("taskId")String taskId,@PathVariable("approved") Boolean approved,@PathVariable("remarks") String remarks) {
        startProcessInstance.approveHolidayRequest(taskId, approved, remarks);
    }

}
