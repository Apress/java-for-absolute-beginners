package com.apress.bgn.ch1.service;

import com.apress.bgn.ch0.service.NakedService;

public class Provider implements NakedService {

    @Override
    public String theSecret() {
        return "*";
    }
}
