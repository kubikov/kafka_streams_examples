/*
 * This file is generated by jOOQ.
 */
package demo.kireev.notificationsinkservice.jooq;


import demo.kireev.notificationsinkservice.jooq.tables.Notification;
import demo.kireev.notificationsinkservice.jooq.tables.OutboxEvent;
import demo.kireev.notificationsinkservice.jooq.tables.Payment;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.notification</code>.
     */
    public final Notification NOTIFICATION = Notification.NOTIFICATION;

    /**
     * The table <code>public.outbox_event</code>.
     */
    public final OutboxEvent OUTBOX_EVENT = OutboxEvent.OUTBOX_EVENT;

    /**
     * The table <code>public.payment</code>.
     */
    public final Payment PAYMENT = Payment.PAYMENT;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Notification.NOTIFICATION,
            OutboxEvent.OUTBOX_EVENT,
            Payment.PAYMENT
        );
    }
}
