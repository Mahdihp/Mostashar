INSERT INTO USERS(id,uid,username,firstname,password,lastname,creationdate, modificationdate,mobile_number)
 VALUES(1,'b5dc7528-1d44-4ae3-9dc0-c3b8212d45a6','admin','admin',
 '$2a$10$1.K8/YCYxkMGjJajQ8BSBuvu4aPWt1qR87zfOGGe9MN14XhAZNJb6','admin',1546426428489,1546426428489,'09134567890');
INSERT INTO roles(id, uid, name, userdefined, description)VALUES (1, 'b4482c30-b3a5-4401-a274-016bda28fdce', 'ADMIN', true, 'admin');


-- INSERT INTO ROLES(id,uid,name,description) VALUES(1,'68d9d762-383c-41e0-9468-26a3902e7b8e','ADMIN','admin role');
--     VALUES (1,'cc808eb0-d393-4505-8afc-dc1bacb1b198',
--     'ADMIN','admin','ali','110',1546614156,1546614346,'091943444444');

-- id bigint NOT NULL, uid UUID NOT NULL,name varchar(255),description varchar(255),groupkey
INSERT INTO features(id, uid, name, description)VALUES (1, 'ddf17e52-1c1a-4a95-807e-e0fbb53b9e70', 'ADMIN', 'admin');
INSERT INTO features(id,description,name,uid) VALUES(2,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e6f9b9bd-9396-4b78-a5e8-569ff236d991');
INSERT INTO features(id,description,name,uid) VALUES(3,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','3427cefe-489c-48b2-9da5-28d7580967df');
INSERT INTO features(id,description,name,uid) VALUES(4,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','22d9c127-c3a6-4f8b-9177-f45332dcd24c');
INSERT INTO features(id,description,name,uid) VALUES(5,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e3af8b98-c539-4df1-adb5-95fc4015ed95');
INSERT INTO features(id,description,name,uid) VALUES(6,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','7008df86-5b1b-456c-b86f-3288d81e6d4b');
INSERT INTO features(id,description,name,uid) VALUES(7,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','ad0bfd24-a80a-4762-af11-d42542195fc5');


INSERT INTO role_feature(roleid,featureid) VALUES (1,1);
INSERT INTO role_feature(roleid,featureid) VALUES (1,2);
