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

-- REPLACE INTO user_roles VALUES
-- ("FKbhsm8kfi2wv0q1j4hqfkiy55o", "ROLE_ADMIN"),
-- ("FKrc4tsdyh4bifeova984j3bugr", "ROLE_MERCHANT"),
-- ("FK4wdjpiuhg2u14dx09poivlmri", "ROLE_BUYER"),
-- ("FKjyl73hgoxq0hjalhx4on4cf1", "ROLE_BUYER"),
-- ("FKfg7xadxcbpdlpw5wgp6ynd2wg", "ROLE_BUYER"),
-- ("FKkv46dn3qakjvsk7ra33nd5sns", "ROLE_BUYER");

REPLACE INTO merchants (user_id,biz_name,office_phone_1, can_sell) VALUES 
(2,"Walco Mart","641 819 1136", TRUE),(7,"Pacific Markets","641 819 1136", TRUE);