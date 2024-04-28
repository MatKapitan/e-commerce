-- DROP SCHEMA public;
--
--CREATE SCHEMA public AUTHORIZATION postgres;
--grant usage on schema public to public;
--revoke usage on schema public from public;
--SET search_path TO public;

-- DROP SEQUENCE public.address_id_seq;

CREATE SEQUENCE public.address_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.country_id_seq;

CREATE SEQUENCE public.country_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.order_line_id_seq;

CREATE SEQUENCE public.order_line_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.order_status_id_seq;

CREATE SEQUENCE public.order_status_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.payment_type_id_seq;

CREATE SEQUENCE public.payment_type_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.product_category_id_seq;

CREATE SEQUENCE public.product_category_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.product_id_seq;

CREATE SEQUENCE public.product_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.product_item_id_seq;

CREATE SEQUENCE public.product_item_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.promotion_id_seq;

CREATE SEQUENCE public.promotion_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.review_id_seq;

CREATE SEQUENCE public.review_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.shipping_method_id_seq;

CREATE SEQUENCE public.shipping_method_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.shop_order_id_seq;

CREATE SEQUENCE public.shop_order_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.shopping_card_id_seq;

CREATE SEQUENCE public.shopping_card_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.site_user_id_seq;

CREATE SEQUENCE public.site_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.user_payment_method_id_seq;

CREATE SEQUENCE public.user_payment_method_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.variation_id_seq;

CREATE SEQUENCE public.variation_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.variation_option_id_seq;

CREATE SEQUENCE public.variation_option_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.country definition

-- Drop table

-- DROP TABLE public.country;

CREATE TABLE public.country (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT country_pkey PRIMARY KEY (id)
);


-- public.order_status definition

-- Drop table

-- DROP TABLE public.order_status;

CREATE TABLE public.order_status (
	id bigserial NOT NULL,
	status varchar(255) NULL,
	CONSTRAINT order_status_pkey PRIMARY KEY (id)
);


-- public.payment_type definition

-- Drop table

-- DROP TABLE public.payment_type;

CREATE TABLE public.payment_type (
	id bigserial NOT NULL,
	payment_values varchar(255) NULL,
	CONSTRAINT payment_type_pkey PRIMARY KEY (id)
);


-- public.promotion definition

-- Drop table

-- DROP TABLE public.promotion;

CREATE TABLE public.promotion (
	discount_rate float8 NULL,
	end_date timestamp(6) NULL,
	id bigserial NOT NULL,
	start_date timestamp(6) NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT promotion_pkey PRIMARY KEY (id)
);


-- public.shipping_method definition

-- Drop table

-- DROP TABLE public.shipping_method;

CREATE TABLE public.shipping_method (
	price numeric(38, 2) NULL,
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT shipping_method_pkey PRIMARY KEY (id)
);


-- public.site_user definition

-- Drop table

-- DROP TABLE public.site_user;

CREATE TABLE public.site_user (
	id bigserial NOT NULL,
	email_address varchar(255) NULL,
	"password" varchar(255) NULL,
	phone_number varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT site_user_pkey PRIMARY KEY (id)
);


-- public.address definition

-- Drop table

-- DROP TABLE public.address;

CREATE TABLE public.address (
	default_address bool NULL,
	country_id int8 NULL,
	id bigserial NOT NULL,
	site_user_id int8 NULL,
	address_line_1 varchar(255) NULL,
	address_line_2 varchar(255) NULL,
	city varchar(255) NULL,
	postal_code varchar(255) NULL,
	region varchar(255) NULL,
	street_number varchar(255) NULL,
	unit_number varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT fke54x81nmccsk5569hsjg1a6ka FOREIGN KEY (country_id) REFERENCES public.country(id),
	CONSTRAINT fkoaf7gmkrx8s47vb6ney4d3ovg FOREIGN KEY (site_user_id) REFERENCES public.site_user(id)
);


-- public.product_category definition

-- Drop table

-- DROP TABLE public.product_category;

CREATE TABLE public.product_category (
	id bigserial NOT NULL,
	parent_category_id int8 NULL,
	category_name varchar(255) NULL,
	CONSTRAINT product_category_pkey PRIMARY KEY (id),
	CONSTRAINT fkh9b72s73v7cga3g5pff7xryvi FOREIGN KEY (parent_category_id) REFERENCES public.product_category(id)
);


-- public.promotion_category definition

-- Drop table

-- DROP TABLE public.promotion_category;

CREATE TABLE public.promotion_category (
	category_id int8 NOT NULL,
	promotion_id int8 NOT NULL,
	CONSTRAINT fk2hpidj3gkbv9vw6980fgaugyw FOREIGN KEY (category_id) REFERENCES public.product_category(id),
	CONSTRAINT fki6u8xhobf3x84rxy6ccou7k0l FOREIGN KEY (promotion_id) REFERENCES public.promotion(id)
);


-- public.user_payment_method definition

-- Drop table

-- DROP TABLE public.user_payment_method;

CREATE TABLE public.user_payment_method (
	default_payment bool NULL,
	expiry_date timestamp(6) NULL,
	id bigserial NOT NULL,
	payment_type_id int8 NULL,
	site_user_id int8 NULL,
	account_number varchar(255) NULL,
	provider varchar(255) NULL,
	CONSTRAINT user_payment_method_pkey PRIMARY KEY (id),
	CONSTRAINT fkeimijqyji4f1r2p8q1ajuwo1w FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id),
	CONSTRAINT fkhd5at1nc5yhrjrspbi3gr0eo5 FOREIGN KEY (site_user_id) REFERENCES public.site_user(id)
);


-- public.variation definition

-- Drop table

-- DROP TABLE public.variation;

CREATE TABLE public.variation (
	category_id int8 NULL,
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT variation_pkey PRIMARY KEY (id),
	CONSTRAINT fkpfb6yow7uvvugoe8oao2cal11 FOREIGN KEY (category_id) REFERENCES public.product_category(id)
);


-- public.variation_option definition

-- Drop table

-- DROP TABLE public.variation_option;

CREATE TABLE public.variation_option (
	id bigserial NOT NULL,
	variation_id int8 NULL,
	value varchar(255) NULL,
	CONSTRAINT variation_option_pkey PRIMARY KEY (id),
	CONSTRAINT fklfkypq92cr21b9mtc7mihks1e FOREIGN KEY (variation_id) REFERENCES public.variation(id)
);


-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	id bigserial NOT NULL,
	product_category_id int8 NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	product_image varchar(255) NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT fkcwclrqu392y86y0pmyrsi649r FOREIGN KEY (product_category_id) REFERENCES public.product_category(id)
);


-- public.product_item definition

-- Drop table

-- DROP TABLE public.product_item;

CREATE TABLE public.product_item (
	price numeric(38, 2) NULL,
	id bigserial NOT NULL,
	product_id int8 NULL,
	qty_in_stock int8 NULL,
	product_image varchar(255) NULL,
	sku varchar(255) NULL,
	CONSTRAINT product_item_pkey PRIMARY KEY (id),
	CONSTRAINT fka9mjpi98ark8eovbtnnreygbb FOREIGN KEY (product_id) REFERENCES public.product(id)
);


-- public.shop_order definition

-- Drop table

-- DROP TABLE public.shop_order;

CREATE TABLE public.shop_order (
	order_total numeric(38, 2) NULL,
	address_id int8 NULL,
	id bigserial NOT NULL,
	order_date timestamp(6) NULL,
	order_status_id int8 NULL,
	shipping_method_id int8 NULL,
	site_user_id int8 NULL,
	user_payment_method_id int8 NULL,
	CONSTRAINT shop_order_pkey PRIMARY KEY (id),
	CONSTRAINT fk2d9s0x98e4417f2gpus19xliv FOREIGN KEY (user_payment_method_id) REFERENCES public.user_payment_method(id),
	CONSTRAINT fk7qvgu9j9j0webqibpbjggiq10 FOREIGN KEY (address_id) REFERENCES public.address(id),
	CONSTRAINT fkimagwsppeifwin0b1xxp4fs5s FOREIGN KEY (order_status_id) REFERENCES public.order_status(id),
	CONSTRAINT fkqxd92b1s09chqaqdv6d64wmmh FOREIGN KEY (site_user_id) REFERENCES public.site_user(id),
	CONSTRAINT fkr071vaj9u2aey781nm0hj09y7 FOREIGN KEY (shipping_method_id) REFERENCES public.shipping_method(id)
);


-- public.shopping_card definition

-- Drop table

-- DROP TABLE public.shopping_card;

CREATE TABLE public.shopping_card (
	id bigserial NOT NULL,
	product_item_id int8 NULL,
	quantity int8 NULL,
	site_user_id int8 NULL,
	CONSTRAINT shopping_card_pkey PRIMARY KEY (id),
	CONSTRAINT fkb4kbvcwnwhmi8pdt77deo114n FOREIGN KEY (site_user_id) REFERENCES public.site_user(id),
	CONSTRAINT fkhvnmcdh8wu7bsvv61s3995smf FOREIGN KEY (product_item_id) REFERENCES public.product_item(id)
);


-- public.order_line definition

-- Drop table

-- DROP TABLE public.order_line;

CREATE TABLE public.order_line (
	price numeric(38, 2) NULL,
	id bigserial NOT NULL,
	product_item_id int8 NULL,
	quantity int8 NULL,
	shop_order_id int8 NULL,
	CONSTRAINT order_line_pkey PRIMARY KEY (id),
	CONSTRAINT fk7ey9gwixnon653pg5kukurf9o FOREIGN KEY (shop_order_id) REFERENCES public.shop_order(id),
	CONSTRAINT fka8w7lfrwdh7muehfgi8tvek4q FOREIGN KEY (product_item_id) REFERENCES public.product_item(id)
);


-- public.product_configuration definition

-- Drop table

-- DROP TABLE public.product_configuration;

CREATE TABLE public.product_configuration (
	product_item_id int8 NOT NULL,
	variation_option_id int8 NOT NULL,
	CONSTRAINT fkd9wo8k6srbadxvi3l87wfqnfx FOREIGN KEY (product_item_id) REFERENCES public.product_item(id),
	CONSTRAINT fkm5sgep9e3ot2covfpw9l3ml7s FOREIGN KEY (variation_option_id) REFERENCES public.variation_option(id)
);


-- public.review definition

-- Drop table

-- DROP TABLE public.review;

CREATE TABLE public.review (
	id bigserial NOT NULL,
	rating_value int8 NULL,
	shop_order_id int8 NULL,
	site_user_id int8 NULL,
	"comment" varchar(255) NULL,
	CONSTRAINT review_pkey PRIMARY KEY (id),
	CONSTRAINT fk48gpk5dfsgakwjce25wcwrur1 FOREIGN KEY (shop_order_id) REFERENCES public.shop_order(id),
	CONSTRAINT fke5yf29917rr4l6l325jw73hf5 FOREIGN KEY (site_user_id) REFERENCES public.site_user(id)
);