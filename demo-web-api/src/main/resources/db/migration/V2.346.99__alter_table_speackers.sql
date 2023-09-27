ALTER TABLE speackers
    ADD COLUMN `created_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3);
ALTER TABLE speackers
    ADD COLUMN `updated_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3);