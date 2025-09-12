package com.example.demo.util;

import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

/**
 * UUIDv7を作成する際に使用するクラス
 */
public class IdGenerator {

    private static final TimeBasedEpochGenerator GENERATOR;
    static {
        GENERATOR = Generators.timeBasedEpochGenerator();
    }

    public static UUID generateId() {
        return GENERATOR.generate();
    }
}
