INSERT IGNORE INTO role (id,type) VALUES (1,"ROLE_BUYER"), (2,"ROLE_MERCHANT"), (3,"ROLE_ADMIN");
INSERT IGNORE INTO user (username, enabled, first_name, last_name, password, user_id, role_id) VALUES
("admin@miu.edu", TRUE, "Admin", "Super Privileged", "admin", "FKbhsm8kfi2wv0q1j4hqfkiy55o",3), /* id: 1 */
("faculty@miu.edu", TRUE, "Faculty", "Medium privileged", "faculty", "FKrc4tsdyh4bifeova984j3bugr",2), /* id: 2 */
("user@miu.edu", TRUE, "User", "Low Privileged", "user", "FK4wdjpiuhg2u14dx09poivlmri",1), /* id: 3 */
("eauser1@miu.edu", TRUE, "EA Student 1", "Low Privileged", "eauser1", "FKjyl73hgoxq0hjalhx4on4cf1",1), /* id: 4 */
("eauser2@miu.edu", TRUE, "EA Student 2", "Low Privileged", "eauser2", "FKfg7xadxcbpdlpw5wgp6ynd2wg",1), /* id: 5 */
("eauser3@miu.edu", TRUE, "EA Student 3", "Low Privileged", "eauser3", "FKkv46dn3qakjvsk7ra33nd5sns",1); /* id: 6 */

-- REPLACE INTO user_roles VALUES
-- ("FKbhsm8kfi2wv0q1j4hqfkiy55o", "ROLE_ADMIN"),
-- ("FKrc4tsdyh4bifeova984j3bugr", "ROLE_MERCHANT"),
-- ("FK4wdjpiuhg2u14dx09poivlmri", "ROLE_BUYER"),
-- ("FKjyl73hgoxq0hjalhx4on4cf1", "ROLE_BUYER"),
-- ("FKfg7xadxcbpdlpw5wgp6ynd2wg", "ROLE_BUYER"),
-- ("FKkv46dn3qakjvsk7ra33nd5sns", "ROLE_BUYER");

REPLACE INTO merchants (user_id,office_phone_1, can_sell) VALUES (
"FKrc4tsdyh4bifeova984j3bugr","641 819 1136", TRUE
);