package com.example.sprinboot_quiz_app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response 
{    
    private Integer id;
    private String response;
}
