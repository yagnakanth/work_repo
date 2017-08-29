INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, FORMAT) VALUES ('Game', 'Dead Space 3 Limited Edition', 'Dead Space 3 brings Isaac Clarke and merciless soldier John Carver on a journey across space to discover the source of the Necromorph outbreak.', 'PS3');
INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, FORMAT) VALUES ('Game', 'Hitman Absolution Professional Edition', 'From a balcony overlooking a roof-top party, you are tasked with taking out Richard Strong, Jr., CEO of Stallion Armaments, along with his entire staff of bodyguards.', 'XBOX');
INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, FORMAT) VALUES ('Game', 'Operation Flashpoint: Red River', 'Between Afghanistan and China, the world’s next flashpoint is about to erupt. The valleys, towns and mountains of Tajikistan will become bloody', 'PC');

INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, ISBN) VALUES ('Book', 'A Long Walk to Freedom', 'The autobiography of one of the greatest men of the twentieth century reissued to coincide with the publication of Anthony Holden''s biography coming from HarperCollins', '9780349106533');
INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, ISBN) VALUES ('Book', 'Politically Incorrect', 'Throughout his tenure as the first black Springbok coach, Peter de Villiers was in the news, and not always for the right reasons.', '9781770224216');
INSERT INTO PRODUCT (DTYPE, TITLE, SHORT_DESCRIPTION, ISBN) VALUES ('Book', 'The Bone Bed', 'First a leading dinosaur hunter goes missing from a dig in Canada. Next, at the Cambridge Forensic Center, Kay Scarpetta received a grisly', '9781408703458');

INSERT INTO SUPPLIER (NAME) VALUES ('WE ARE GAMES');
INSERT INTO SUPPLIER (NAME) VALUES ('A TINY LITTLE BOOKSTORE');

INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('10.00', '10.00', 1, 1);
INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('10.00', '15.00', 1, 1); INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('10.00', '20.00', 1, 1);

INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('05.00', '9.995', 2, 1);
INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('10.00', '11.122', 3, 1);
INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('15.00', '23.0033', 4, 2);
INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('20.00', '22.0011', 5, 2);
INSERT INTO ITEM_PRICE (COST_PRICE, SELLING_PRICE, PRODUCT_ID, SUPPLIER_ID) VALUES ('25.00', '19.99', 6, 2);

INSERT INTO USER (DTYPE, LOGIN_NAME, PASSWORD, FIRST_NAME, LAST_NAME) VALUES ('Person', 'johnd', 'password', 'John', 'Doe');
INSERT INTO USER (DTYPE, LOGIN_NAME, PASSWORD, COMPANY_NAME, VAT_NO) VALUES ('Company', 'acme', 'password', 'ACME E-Commerce', '012345');