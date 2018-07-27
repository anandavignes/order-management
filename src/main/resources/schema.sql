-- DDL statements for Order Management micro service ###

CREATE TABLE orders (id NUMBER(25) NOT NULL UNIQUE, STATUS VARCHAR2(30) NOT NULL, LAST_UPD_DT DATE NOT NULL );
CREATE TABLE products(id NUMBER(25) NOT NULL UNIQUE, order_id NUMBER(25), sku VARCHAR2(50) NOT NULL, price DECIMAL(10,2), quantity NUMBER(10), 
FOREIGN KEY (order_id) references orders(id));
