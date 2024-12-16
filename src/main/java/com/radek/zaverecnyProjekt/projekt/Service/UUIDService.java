package com.radek.zaverecnyProjekt.projekt.Service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {
    public UUID generatedUuid(){
        return UUID.randomUUID();
    }
}

