INSERT INTO USERS(id,uid,username,firstname,password,lastname,created_at,updated_at,mobile_number) VALUES(1,'b5dc7528-1d44-4ae3-9dc0-c3b8212d45a6','admin','admin','$2a$10$1.K8/YCYxkMGjJajQ8BSBuvu4aPWt1qR87zfOGGe9MN14XhAZNJb6','admin','2018-12-14 22:17:02.615','2018-12-14 22:17:02.615','09134567890');

INSERT INTO ROLES(id,uid,name,description,created_at,updated_at) VALUES(1,'68d9d762-383c-41e0-9468-26a3902e7b8e','ADMIN','admin role','2018-12-14 22:17:02.585','2018-12-14 22:17:02.585');

INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(1,'2018-12-14 22:17:02.458','2018-12-14 22:17:02.458','ACCESS_TO_UPDATE_ONE','ACCESS_TO_UPDATE_ONE','e6f9b9bd-9396-4b78-a5e8-569ff236d991');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(2,'2018-12-14 22:17:02.519','2018-12-14 22:17:02.519','ACCESS_TO_UPDATE_ALL','ACCESS_TO_UPDATE_ALL','1c34bf3c-94ea-4bb0-827c-2d201c137058');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(3,'2018-12-14 22:17:02.532','2018-12-14 22:17:02.532','ACCESS_TO_ADD','ACCESS_TO_ADD','91897f08-c50b-4050-b702-1231dc0b457d');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(4,'2018-12-14 22:17:02.541','2018-12-14 22:17:02.541','ACCESS_TO_SEARCH','ACCESS_TO_SEARCH','7ccb2861-365a-4ad5-9f80-ab2c80d063b3');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(5,'2018-12-14 22:17:02.55','2018-12-14 22:17:02.55','ACCESS_TO_DETECT_CHANGES','ACCESS_TO_DETECT_CHANGES','e037ecf2-ec60-427e-95fa-61acafa8eb8a');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(6,'2018-12-14 22:17:02.558','2018-12-14 22:17:02.558','ACCESS_TO_USER_API','ACCESS_TO_USER_API','3da2009d-d9a0-42d9-a243-c6bb3e874ca8');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(7,'2018-12-14 22:17:02.567','2018-12-14 22:17:02.567','ACCESS_TO_ROLE_API','ACCESS_TO_ROLE_API','90110991-6414-4858-8ad0-052e10549b6c');
INSERT INTO FEATURES(id,created_at,updated_at,description,name,uid) VALUES(8,'2018-12-14 22:17:02.575','2018-12-14 22:17:02.575','ACCESS_TO_DELETE','ACCESS_TO_DELETE','f090fc3d-890a-4a86-8dbd-55aa73fdbdcc');

INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,1);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,2);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,3);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,4);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,5);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,6);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,7);
INSERT INTO ROLE_FEATURES(role_id,feature_id) VALUES (1,8);

INSERT INTO USER_ROLES(user_id,role_id) VALUES (1,1);
