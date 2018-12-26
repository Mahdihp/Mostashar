CREATE TABLE USERS (id bigint NOT null, uid UUID, username varchar(255), firstname varchar(255),fathername varchar(255),nationalid varchar(10),birthdate bigint, password Text, lastname varchar(255),email varchar(255),score integer,avatarhashcode varchar(255) ,isonline boolean,isactive boolean, creationdate timestamp, modificationdate timestamp, mobile_number varchar(255), PRIMARY KEY (id));
ALTER TABLE USERS ADD CONSTRAINT UK_46kvy05nqwkhpefsagvg5samv  UNIQUE (username);
ALTER TABLE USERS ADD CONSTRAINT UK_k3gm4drnorissx5s1hmnk99cj  UNIQUE (uid);
ALTER TABLE USERS ADD CONSTRAINT UK_68tlf4oult9ywhvk6dfmvf1r2  UNIQUE (mobile_number);

CREATE TABLE ROLES (id bigint NOT null, uid UUID, name varchar(255),userdefined boolean, description varchar(255), created_at timestamp, updated_at timestamp, PRIMARY KEY (id));
ALTER TABLE ROLES ADD CONSTRAINT UK_hffrgdy49ff56f4w6t6pmj288  UNIQUE (uid);
ALTER TABLE ROLES ADD CONSTRAINT UK_1s6p3xpt8owdb603jky0mo815  UNIQUE (name);

CREATE TABLE wallets(id bigint NOT null, uid UUID,value integer,bankAccountName varchar(255),bankAccountNumber varchar(255),bankAccountSheba  varchar(255));

CREATE TABLE Settings(id bigint NOT null, uid UUID,description varchar(255),userdefined boolean);

CREATE TABLE AccessEntrys(id bigint NOT null, uid UUID,type integer ,name varchar(255),description varchar(255),creationdate timestamp,modificationdate timestamp,expirydate timestamp  );

CREATE TABLE InvitedUsers(id bigint NOT null, uid UUID,invitedusername varchar(255),creationdate timestamp);

CREATE TABLE AssignDiscounts(id bigint NOT null, uid UUID,isactive boolean,creationdate timestamp,modificationdate timestamp,expirydate timestamp) ;

CREATE TABLE Devices(id bigint NOT null, uid UUID,ipaddress varchar(15),model varchar(255),imei varchar(20));

CREATE TABLE Reminders(id bigint NOT null, uid UUID,read varchar(255));

CREATE TABLE SharingPerspectives(id bigint NOT null, uid UUID, membershiptype varchar(255),creationdate timestamp,modificationdate timestamp,expirydate timestamp);

CREATE TABLE Lawyers(id bigint NOT null, uid UUID,isavailable boolean,level integer);

CREATE TABLE Clients(id bigint NOT null, uid UUID,jobtitle varchar(255),address varchar(255),postalcode varchar(255),fieldofstudy varchar(255),telephone varchar(255));

CREATE TABLE FEATURES (id bigint NOT null, uid UUID, name varchar(255), description varchar(255), created_at timestamp, updated_at timestamp, PRIMARY KEY (id));
ALTER TABLE FEATURES ADD CONSTRAINT UK_c8pkbrgxfl8ghr7b75hqr8eqv  UNIQUE (uid);
ALTER TABLE FEATURES ADD CONSTRAINT UK_qx1jf10esv72lwsb4d8xmbyh8  UNIQUE (name);

CREATE TABLE ROLE_FEATURES (role_id bigint NOT null, feature_id bigint NOT null, PRIMARY KEY (role_id,feature_id));
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_23kfmjd6lt9jgjcxsgp8nmtbp FOREIGN KEY (feature_id) REFERENCES FEATURES;
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_iyd1dkloxejnurxgx72c9pwvg FOREIGN KEY (role_id) REFERENCES ROLES;

CREATE TABLE USER_ROLES (role_id bigint NOT null, user_id bigint NOT null, PRIMARY KEY (user_id, role_id));
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_ixvn2eap6pna97ymmcm11narh FOREIGN KEY (user_id) REFERENCES USERS;
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_4vocb5yb32s4kl5a9hax6h4sa FOREIGN KEY (role_id) REFERENCES ROLES;