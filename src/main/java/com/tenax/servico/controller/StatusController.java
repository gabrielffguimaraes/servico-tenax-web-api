package com.tenax.servico.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Status", description = "checar status da aplicação")
public class StatusController {
    @GetMapping("/status")
    @Operation(summary = "Status da aplicação")
    public String status() {
        return "Online .";
    }
}
