private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>(
			0);
INSERT INTO users(id,uid,username,firstname,password,lastname,creationdate, modificationdate,mobile_number) VALUES(1,'b5dc7528-1d44-4ae3-9dc0-c3b8213d45a6','admin','mahdi', '$2a$10$1.K8/YCYxkMGjJajQ8BSBuvu4aPWt1qR87zfOGGe9MN14XhAZNJb2','hp',1546426428489,1546426428489,0913452890);

INSERT INTO roles(id,uid,name,userdefined,description)VALUES (1, 'b4482c30-b3a5-4401-a274-016bda28fdce', 'ADMIN', true, 'admin');
INSERT INTO roles(id,uid,name,userdefined,description)VALUES (2, 'b4482c40-b3a5-4401-a274-016bda28fdce', 'GUEST', true, 'admin');


INSERT INTO features(id,description,name,uid) VALUES(1,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e6f9b9bd-9396-4b78-a5e8-569ff236d991');
INSERT INTO features(id,description,name,uid) VALUES(2,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','3427cefe-489c-48b2-9da5-28d7580967df');
INSERT INTO features(id,description,name,uid) VALUES(3,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','22d9c127-c3a6-4f8b-9177-f45332dcd24c');
INSERT INTO features(id,description,name,uid) VALUES(4,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e3af8b98-c539-4df1-adb5-95fc4015ed95');
INSERT INTO features(id,description,name,uid) VALUES(5,'ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','7008df86-5b1b-456c-b86f-3288d81e6d4b');


INSERT INTO role_feature(roleid,featureid) VALUES (1,1);
INSERT INTO role_feature(roleid,featureid) VALUES (2,2);
