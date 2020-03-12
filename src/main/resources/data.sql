INSERT IGNORE INTO product_category (category, tax_in_percentage) VALUES ("Electronics", 0.54), ("Computer", 1.09), ("Fashion",1.54), ("Building Tools",2.43), ("Jewellery", 1.23);
INSERT IGNORE INTO roles (id,type) VALUES (1,"ROLE_BUYER"), (2,"ROLE_MERCHANT"), (3,"ROLE_ADMIN");
INSERT IGNORE INTO users (username, enabled, first_name, last_name, password, role_id) VALUES
("admin@miu.edu", TRUE, "Admin", "Super Privileged", "admin" ,3), /* id: 1 */
("faculty@miu.edu", TRUE, "Faculty", "Medium privileged", "faculty",2), /* id: 2 */
("user@miu.edu", TRUE, "User", "Low Privileged", "user", 1), /* id: 3 */
("eauser1@miu.edu", TRUE, "EA Student 1", "Low Privileged", "eauser1" ,1), /* id: 4 */
("eauser2@miu.edu", TRUE, "EA Student 2", "Low Privileged", "eauser2", 1), /* id: 5 */
("eauser3@miu.edu", TRUE, "Merchant", "Walmart", "eauser3", 2),
("eauser4@miu.edu", TRUE, "Merchant", "Target", "eauser4", 2);  /* id: 6 */

REPLACE INTO merchants (user_id,biz_name,office_phone_1, can_sell) VALUES 
(2,"Walco Mart","641 819 1136", TRUE),(7,"Pacific Markets","641 819 1136", TRUE),(6,"Amazon Techs","641 819 1136", TRUE);

INSERT IGNORE INTO products (description,discount,price, qty_avail, summary, title, category_id, merchant_id,is_available) VALUES 
("Plasma TV from LG is so beautifully crafted",6, 256.98, 13, "LG Plasma TV ", "LG Plasma TV 32 Inch Screen",1,2,1),
("Facade Jewelleries is fitted for all occcasions",4, 1996.98, 12, "Facade Gold Necklace 23 Karate Gold", "Facade Gold Necklace 23 Karate Gold",3,7,1),
("Brand New Laptop",2, 456.98, 123, "HP Envy is a Great Laptop", "HP Envy GH456",2,2,1),
("Plasma TV from LG is so beautifully crafted",6, 256.98, 13, "LG Plasma TV ", "LG Plasma TV 32 Inch Screen",1,1,1),
("Facade Jewelleries is fitted for all occcasions",4, 1996.98, 12, "Facade Gold Necklace 23 Karate Gold", "Facade Gold Necklace 23 Karate Gold",3,2,1),
("Brand New Laptop",2, 456.98, 123, "HP Envy is a Great Laptop", "HP Envy GH456",2,6,1),
("Plasma TV from LG is so beautifully crafted",6, 256.98, 13, "LG Plasma TV ", "LG Plasma TV 32 Inch Screen",1,6,1),
("Facade Jewelleries is fitted for all occcasions",4, 1996.98, 12, "Facade Gold Necklace 23 Karate Gold", "Facade Gold Necklace 23 Karate Gold",3,6,1),
("Brand New Laptop",2, 456.98, 123, "HP Envy is a Great Laptop", "HP Envy GH456",2,6,1),
("Brand New Laptop",2, 496.98, 123, "HP Envy is a Great Laptop", "HP Envy GH456 20GB RAM",2,7,1);