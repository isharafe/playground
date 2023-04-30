INSERT INTO stock (eid, version, name, description) VALUES
(1, 1, 'stock-1', 'Stock 1'),
(2, 1, 'stock-2', 'Stock 2');

INSERT INTO stock_item (eid, version, product_id, quantity, stock_eid) VALUES
(1, 1, 'p001', 10, 1);
