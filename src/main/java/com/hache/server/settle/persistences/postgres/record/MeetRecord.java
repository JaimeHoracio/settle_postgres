package com.hache.server.settle.persistences.postgres.record;

import java.util.Date;
import java.util.Set;

public record MeetRecord(
        String idMeet,
        Boolean active,
        String name,
        Date created,
        Date updated,
        Set<BillRecord> listBill
) {
}
