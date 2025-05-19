
INSERT INTO usuario (id, name,last_name, username, email, password,phone_number,ACCOUNT_NON_EXPIRED,ACCOUNT_NON_LOCKED,CREDENTIALS_NON_EXPIRED,enabled,foto_url) VALUES ('5d818565-99f9-4d80-920e-8259c6ecb8e6', 'Pedro','pepe', 'ToRechulon', 'pedro@gmail.com', '{bcrypt}$2a$10$05HASeZdtwl8NS/nWbNMJOU07tiGZ9Z/mVE2Z.FKhsyjkCK7yuLqa',383838,true,true,true,true,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQciKN1y59CDYMq-IALg7OUijN7hIiM8hdzKw&usqp=CAU');
INSERT INTO usuario_roles ( roles,usuario_id) VALUES (0,'5d818565-99f9-4d80-920e-8259c6ecb8e6');

INSERT INTO Servicios (id,tipo) values('89aec657-50b2-4dca-a4b8-45371a135399','Corte de pelo mas barba');
INSERT INTO Servicios (id,tipo) values('8463a4f0-6e79-4980-8f4d-ab573a57edfe','Corte de pelo mas loquesea');
INSERT INTO Servicios (id,tipo) values('b0695087-71b2-4bab-9d55-9cbe65843a40','Corte de pelo mas barba 2');
INSERT INTO Servicios (id,tipo) values('eccfdaf5-f5b5-49e4-af57-c446ccdeb092','Corte de pelo mas barba  5');


INSERT INTO Estilistas(id,nombre) values('564ea3b0-1cf4-46ed-a729-1d11b6958a81','Mimi');
INSERT INTO Estilistas(id,nombre) values('bd87eaa8-9899-46ec-b3de-7b72216a6578','Alvaro Leo');
