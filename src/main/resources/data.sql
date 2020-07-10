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


INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (1, 'Hyoga', 4, 'CASH', 'http://localhost:8080/gateways/pay', 'BR');
INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (2, 'Saori', 0.01, 'PERCENTAGE', 'http://localhost:8080/gateways/pay', 'BR');
INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (3, 'Seya', 5, 'CASH', 'http://localhost:8080/gateways/pay', 'BR');
INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (4, 'Shiryu', 3.5, 'CASH', 'http://localhost:8080/gateways/pay', 'BR');
INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (5, 'Shun', 4, 'CASH', 'http://localhost:8080/gateways/pay', 'BR');
INSERT INTO payment_gateways (payment_gateway_id, gateway_name, fare, fare_type, gateway_uri, country) VALUES (6, 'Tango', 5), 'CASH', 'http://localhost:8080/gateways/pay', 'ARG';

INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 1);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 1);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('HYPERCARD', 1);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 2);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 2);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('HYPERCARD', 2);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('ELO', 2);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 3);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 3);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('HYPERCARD', 3);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('ELO', 3);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 4);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 4);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 5);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 5);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('ELO', 5);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('VISA', 6);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('MASTERCARD', 6);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('HYPERCARD', 6);
INSERT INTO payment_gateways_cards(card_flag, payment_gateway_id) VALUES ('ELO', 6);