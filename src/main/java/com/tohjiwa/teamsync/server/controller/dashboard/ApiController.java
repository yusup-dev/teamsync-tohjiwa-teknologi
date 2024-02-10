package com.tohjiwa.teamsync.server.controller.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard-api/")
public class ApiController {

    @GetMapping("/data")
    public Map<String, String> data() {
        var a = new HashMap<String, String>();
        a.put("Makan", "Nasi");

        return a;
    }
}
