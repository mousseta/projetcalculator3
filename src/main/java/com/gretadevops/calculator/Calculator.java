package com.gretadevops.calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    public int sum(int a, int b) { // Visibilité publique
        return a + b;
    }
}
