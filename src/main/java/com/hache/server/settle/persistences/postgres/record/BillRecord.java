package com.hache.server.settle.persistences.postgres.record;

import java.util.Date;

public record BillRecord(
        String idBill,
        String reference,
        Date created
) {
}
