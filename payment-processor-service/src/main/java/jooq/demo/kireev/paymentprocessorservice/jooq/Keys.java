/*
 * This file is generated by jOOQ.
 */
package demo.kireev.paymentprocessorservice.jooq;


import demo.kireev.paymentprocessorservice.jooq.tables.Notification;
import demo.kireev.paymentprocessorservice.jooq.tables.OutboxEvent;
import demo.kireev.paymentprocessorservice.jooq.tables.Payment;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<Record> NOTIFICATION_PKEY = Internal.createUniqueKey(Notification.NOTIFICATION, DSL.name("notification_pkey"), new TableField[] { Notification.NOTIFICATION.ID }, true);
    public static final UniqueKey<Record> OUTBOX_EVENT_PKEY = Internal.createUniqueKey(OutboxEvent.OUTBOX_EVENT, DSL.name("outbox_event_pkey"), new TableField[] { OutboxEvent.OUTBOX_EVENT.ID }, true);
    public static final UniqueKey<Record> PAYMENT_PKEY = Internal.createUniqueKey(Payment.PAYMENT, DSL.name("payment_pkey"), new TableField[] { Payment.PAYMENT.ID }, true);
}
