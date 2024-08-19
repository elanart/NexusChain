package com.nxc.configs;

import com.nxc.service.DataLoaderService;
import com.nxc.service.InitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class WebInitializerConfig {
    private final InitializerService service;
    private final DataLoaderService dataLoaderService;

    @PostConstruct
    public void initialize() {
        this.service.createAdminUser();
        this.dataLoaderService.loadData();
    }
}
