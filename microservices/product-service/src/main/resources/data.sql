INSERT INTO product_category (eid, version, category_id, name, description) VALUES
(1, 1, 'cat-001', 'category name 1', 'category description 1'),
(2, 1, 'cat-002', 'category name 2', 'category description 2');

INSERT INTO product (eid, version, name, description, image, category_id) VALUES
(1, 1, 'product name 1', 'product description 1', 'test/image1.png', 1),
(2, 1, 'product name 2', 'product description 2', 'test/image2.png', 1),
(3, 1, 'product name 3', 'product description 3', 'test/image3.png', 1),
(4, 1, 'product name 4', 'product description 4', 'test/image4.png', 1),
(5, 1, 'product name 5', 'product description 5', 'test/image5.png', 1),
(6, 1, 'product name 6', 'product description 6', 'test/image6.png', 2),
(7, 1, 'product name 7', 'product description 7', 'test/image7.png', 2),
(8, 1, 'product name 8', 'product description 8', 'test/image8.png', 2),
(9, 1, 'product name 9', 'product description 9', 'test/image9.png', 2),
(10, 1, 'diff name 10', 'another description 10', 'test/image10.png', 2);
