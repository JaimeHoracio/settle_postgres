package com.hache.server.settle.persistences.postgres.record;

public record UserBasicRecord(
        String email,
        String name,
        String password
) {}
