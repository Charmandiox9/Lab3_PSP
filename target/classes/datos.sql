-- 🧱 Creación de tablas
CREATE TABLE IF NOT EXISTS customers (
                           id TEXT PRIMARY KEY,
                           join_date TEXT NOT NULL,
                           total_orders INTEGER NOT NULL,
                           level TEXT NOT NULL,
                           has_active_promo INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS logs (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      customer_id TEXT NOT NULL,
                      timestamp TEXT NOT NULL,
                      discount_applied REAL NOT NULL,
                      FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- 📥 Datos para tabla `customers`
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c001', '2015-06-10 00:00:00', 120, 'PLATINUM', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c002', '2020-01-15 00:00:00', 45, 'SILVER', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c003', '2012-09-01 00:00:00', 200, 'GOLD', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c004', '2018-03-22 00:00:00', 75, 'BASIC', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c005', '2010-12-11 00:00:00', 300, 'PLATINUM', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c006', '2019-07-30 00:00:00', 15, 'SILVER', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c007', '2014-02-14 00:00:00', 105, 'GOLD', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c008', '2016-08-19 00:00:00', 88, 'BASIC', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c009', '2021-10-01 00:00:00', 5, 'BASIC', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c010', '2013-05-05 00:00:00', 190, 'PLATINUM', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c011', '2022-02-18 00:00:00', 10, 'SILVER', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c012', '2017-04-04 00:00:00', 62, 'GOLD', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c013', '2011-11-11 00:00:00', 150, 'GOLD', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c014', '2015-01-01 00:00:00', 130, 'SILVER', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c015', '2020-12-12 00:00:00', 20, 'BASIC', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c016', '2016-06-06 00:00:00', 102, 'PLATINUM', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c017', '2013-03-03 00:00:00', 87, 'GOLD', 1);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c018', '2019-09-09 00:00:00', 99, 'SILVER', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c019', '2023-05-05 00:00:00', 3, 'BASIC', 0);
INSERT INTO customers (id, join_date, total_orders, level, has_active_promo) VALUES ('c020', '2014-10-10 00:00:00', 111, 'PLATINUM', 1);

-- 📥 Datos para tabla `logs`
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c001', '2025-06-01 10:30:00', 0.30);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c002', '2025-06-01 10:31:00', 0.05);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c003', '2025-06-01 10:32:00', 0.25);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c004', '2025-06-01 10:33:00', 0.05);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c005', '2025-06-01 10:34:00', 0.30);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c006', '2025-06-01 10:35:00', 0.15);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c007', '2025-06-01 10:36:00', 0.20);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c008', '2025-06-01 10:37:00', 0.15);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c009', '2025-06-01 10:38:00', 0.00);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c010', '2025-06-01 10:39:00', 0.30);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c011', '2025-06-01 10:40:00', 0.05);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c012', '2025-06-01 10:41:00', 0.20);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c013', '2025-06-01 10:42:00', 0.20);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c014', '2025-06-01 10:43:00', 0.20);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c015', '2025-06-01 10:44:00', 0.15);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c016', '2025-06-01 10:45:00', 0.25);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c017', '2025-06-01 10:46:00', 0.25);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c018', '2025-06-01 10:47:00', 0.05);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c019', '2025-06-01 10:48:00', 0.00);
INSERT INTO logs (customer_id, timestamp, discount_applied) VALUES ('c020', '2025-06-01 10:49:00', 0.30);
