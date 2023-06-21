#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "arch" --dbname "arch_test" <<-EOSQL
  CREATE TABLE IF NOT EXISTS public.outbox_event (
    id uuid NOT NULL,
    aggregatetype character varying(255) NOT NULL,
    aggregateid character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    payload jsonb NOT NULL,
    PRIMARY KEY (id)
  );

    CREATE TABLE IF NOT EXISTS public.payment (
      id uuid NOT NULL,
      currency character varying(255) NOT NULL,
      price numeric NOT NULL,
      confirm boolean NOT NULL,
      PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS public.notification (
      id uuid NOT NULL,
      status character varying(255),
      currency character varying(255) NOT NULL,
      price numeric,
      name character varying(255),
      payment_confirm boolean,
      stock_confirm boolean,
      PRIMARY KEY (id)
    );
EOSQL