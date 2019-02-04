
INSERT INTO users(id,uid,mobilenumber) VALUES(1,'b5dc7528-1d44-4ae3-9dc0-c3b8213d45a6',9134528901);
INSERT INTO users(id,uid,mobilenumber) VALUES(2,'b6dc7528-1d44-4ae3-9dc0-c3b8213d45a6',9339466051);
INSERT INTO users(id,uid,mobilenumber) VALUES(3,'b7dc7528-1d44-4ae3-9dc0-c3b8213d45a6',9191544275);
--
INSERT INTO clients(userid,address,tel)VALUES(1,'Qom very good',9339466051);
INSERT INTO clients(userid,address,tel)VALUES(2,'tehran',9339466051);
INSERT INTO clients(userid,address,tel)VALUES(3,'tehran',9339466051);
--
INSERT INTO roles(id,uid,name,userdefined,description)VALUES (1, 'b4482c30-b3a5-4401-a274-016bda28fdce', 'ROLE_ADMIN',  true,  'admin');
INSERT INTO roles(id,uid,name,userdefined,description)VALUES (2, 'b3482c40-b3a5-4401-a274-016bda28fdce', 'ROLE_LAWYER', true, 'lawyer');
INSERT INTO roles(id,uid,name,userdefined,description)VALUES (3, 'b45482c0-b3a5-4401-a274-016ba28fdcee', 'ROLE_CLIENT', true, 'client');


INSERT INTO features(id,description,name,uid) VALUES(1,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e6f9b9bd-9396-4b78-a5e8-569ff236d991');
INSERT INTO features(id,description,name,uid) VALUES(2,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','3427cefe-489c-48b2-9da5-28d7580967df');
INSERT INTO features(id,description,name,uid) VALUES(3,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','22d9c127-c3a6-4f8b-9177-f45332dcd24c');
INSERT INTO features(id,description,name,uid) VALUES(4,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e3af8b98-c539-4df1-adb5-95fc4015ed95');
INSERT INTO features(id,description,name,uid) VALUES(5,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','7008df86-5b1b-456c-b86f-3288d81e6d4b');


INSERT INTO role_feature(roleid,featureid) VALUES (1,1);
INSERT INTO role_feature(roleid,featureid) VALUES (2,2);
