package com.flystonedev.abstracts.model;

public record StatsResponse(
        Long abstractCount,
        Long abstractAccepted,
        Long abstractPending
) {
}
