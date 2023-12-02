package com.nzipo.NzIpoExercise3.controller;

import com.nzipo.NzIpoExercise3.entities.Binder;
import com.nzipo.NzIpoExercise3.entities.CaseValue;
import com.nzipo.NzIpoExercise3.service.NzService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class RobotController {

    @Autowired
    private final NzService nzService;

    @RequestMapping("/complaintRobot")
    public CaseValue scrap() throws IOException, InterruptedException {
        return nzService.scrapping();
    }
}
