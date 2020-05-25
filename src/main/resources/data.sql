INSERT INTO users (user_id, email) VALUES (1, 'buzz@toystory.com');
INSERT INTO users (user_id,email) VALUES (2, 'woody@toystory.com');
INSERT INTO users (user_id,email) VALUES (3, 'lilo@toystory.com');
INSERT INTO users (user_id,email) VALUES (4, 'stich@toystory.com');
INSERT INTO users (user_id,email) VALUES (5, 'moana@toystory.com');


INSERT INTO restaurants (restaurant_id, name) VALUES (1, 'Burger Queen');
INSERT INTO restaurants (restaurant_id, name) VALUES (2, 'Roberts');
INSERT INTO restaurants (restaurant_id, name) VALUES (3, 'Underground');
INSERT INTO restaurants (restaurant_id, name) VALUES (4, 'Dirty');
INSERT INTO restaurants (restaurant_id, name) VALUES (5, 'Olivias');


INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CREDIT_CARD', 1);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CREDIT_CARD', 2);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CASH', 2);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CHECK', 2);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CREDIT_CARD', 3);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CHECK', 3);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CASH', 4);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CHECK', 4);
INSERT INTO user_payment_methods(payment_method, user_id) VALUES ('CASH', 5);


INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CREDIT_CARD', 1);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CASH', 1);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CARD_MACHINE', 1);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CREDIT_CARD', 2);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CARD_MACHINE', 2);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CREDIT_CARD', 3);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CASH', 3);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CARD_MACHINE', 3);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CASH', 4);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CASH', 5);
INSERT INTO restaurants_payment_methods(payment_method, restaurant_id) VALUES ('CHECK', 5);