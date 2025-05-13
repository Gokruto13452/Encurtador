package com.exemplo.encurtadorurl.controller;

import com.exemplo.encurtadorurl.service.UrlService; // corrigido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/encurtar")
    public String encurtar(@RequestBody String url) {
        String code = service.shortenUrl(url);
        return "https://localhost:8080/" + code;
    }

    @GetMapping("/{code}")
    public RedirectView redirecionar(@PathVariable String code) {
        String originalUrl = service.getOriginalUrl(code); // corrigido

        return new RedirectView(originalUrl);
    }
}
