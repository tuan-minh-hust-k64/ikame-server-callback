package com.genai.server.controller;

import com.genai.server.gcp.PubSubHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/api/adjust")
public class AdjustController {
    @GetMapping(value = "/mobile_attribution")
    public ResponseEntity<?> globalTrigger(@RequestParam String name, @RequestParam int age) {
        Map<String, ?> x = new HashMap<>() {{
            put("name", name);
            put("age", age);
        }};
        Gson gson = new Gson();
        Type typeObject = new TypeToken<HashMap>() {}.getType();
        String gsonData = gson.toJson(x, typeObject);
        log.info(gsonData);
        try {
            PubSubHelper.publishWithErrorHandlerExample("ikame-gem-ai-research", "test-1", gsonData);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("OK");
    }
}
