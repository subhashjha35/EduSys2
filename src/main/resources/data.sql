
insert into Teacher(enabled,password,role,username) values(TRUE,'user', 'ROLE_USER', 'user');
insert into Teacher(enabled,password,role,username) values(TRUE,'adam', 'ROLE_USER', 'adam');
insert into Subject(name,code,credit,public_flag,TEACHER_ID) values ('History','SUB1',3,FALSE,1);
insert into Subject(name,code,credit,public_flag,TEACHER_ID) values ('English','SUB1',4,TRUE,1);
insert into Subject(name,code,credit,public_flag,TEACHER_ID) values ('Science','SUB1',2,FALSE,2);
insert into Subject(name,code,credit,public_flag,TEACHER_ID) values ('Geography','SUB1',3,TRUE,2);


