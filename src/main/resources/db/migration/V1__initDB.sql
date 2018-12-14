CREATE TABLE USERS (id bigint NOT null, uid UUID, username varchar(255), firstname varchar(255), password Text, lastname varchar(255), created_at timestamp, updated_at timestamp, mobile_number varchar(255), PRIMARY KEY (id));
ALTER TABLE USERS ADD CONSTRAINT UK_46kvy05nqwkhpefsagvg5samv  UNIQUE (username);
ALTER TABLE USERS ADD CONSTRAINT UK_k3gm4drnorissx5s1hmnk99cj  UNIQUE (uid);
ALTER TABLE USERS ADD CONSTRAINT UK_68tlf4oult9ywhvk6dfmvf1r2  UNIQUE (mobile_number);

CREATE TABLE ROLES (id bigint NOT null, uid UUID, name varchar(255), description varchar(255), created_at timestamp, updated_at timestamp, PRIMARY KEY (id));
ALTER TABLE ROLES ADD CONSTRAINT UK_hffrgdy49ff56f4w6t6pmj288  UNIQUE (uid);
ALTER TABLE ROLES ADD CONSTRAINT UK_1s6p3xpt8owdb603jky0mo815  UNIQUE (name);

CREATE TABLE FEATURES (id bigint NOT null, uid UUID, name varchar(255), description varchar(255), created_at timestamp, updated_at timestamp, PRIMARY KEY (id));
ALTER TABLE FEATURES ADD CONSTRAINT UK_c8pkbrgxfl8ghr7b75hqr8eqv  UNIQUE (uid);
ALTER TABLE FEATURES ADD CONSTRAINT UK_qx1jf10esv72lwsb4d8xmbyh8  UNIQUE (name);

CREATE TABLE ROLE_FEATURES (role_id bigint NOT null, feature_id bigint NOT null, PRIMARY KEY (role_id,feature_id));
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_23kfmjd6lt9jgjcxsgp8nmtbp FOREIGN KEY (feature_id) REFERENCES FEATURES;
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_iyd1dkloxejnurxgx72c9pwvg FOREIGN KEY (role_id) REFERENCES ROLES;

CREATE TABLE USER_ROLES (role_id bigint NOT null, user_id bigint NOT null, PRIMARY KEY (user_id, role_id));
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_ixvn2eap6pna97ymmcm11narh FOREIGN KEY (user_id) REFERENCES USERS;
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_4vocb5yb32s4kl5a9hax6h4sa FOREIGN KEY (role_id) REFERENCES ROLES;