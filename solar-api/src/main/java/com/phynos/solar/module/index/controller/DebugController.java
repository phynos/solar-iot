package com.phynos.solar.module.index.controller;

import com.phynos.solar.module.index.service.DebugService;
import com.phynos.solar.util.json.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lupc
 * @date 2019/10/10 20:24
 **/
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Value("${com.phynos.solar.time}")
    String buildTime;

    @Autowired
    DebugService debugService;

    @GetMapping("/buildTime")
    public R<?> buildTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(buildTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 8);
        date = cal.getTime();
        return new R.Build().put("buildTime", sdf.format(date)).build();
    }

    @GetMapping("/testThread")
    public String testThread() {
        debugService.testThread();
        return "ok";
    }

    @GetMapping("/test")
    public R<?> test() {
        return debugService.test();
    }


}
