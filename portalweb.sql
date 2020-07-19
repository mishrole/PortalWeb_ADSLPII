Drop database portalweb;

Create database if not exists portalweb;
Use portalweb;

/*		Tablas Independientes		*/

Create table rol
(
rol_id int(6) auto_increment not null,
rol_nombre varchar(20) not null,
primary key (rol_id)
);

Insert into rol (rol_nombre) values ('Unidad Orgánica'),('Subgerente'),('Técnico Especialista');

Create table normativa
(
normativa_id int(6) auto_increment not null,
normativa_nombre varchar(20) not null,
primary key (normativa_id)
);

Insert into normativa (normativa_nombre) values ('Acuerdo'),('Decreto'),('Ordenanza'),('Resolución');

Create table estado
(
estado_id int(6) auto_increment not null,
estado_nombre varchar(20) not null,
primary key (estado_id)
);

Insert into estado (estado_nombre) values ('En espera'),('Aprobado'),('Rechazado'),('Atendido'),('Finalizado');

/*		Tablas de Acceso y Usuarios	*/

Create table usuario
(
usuario_id int(6) auto_increment not null,
usuario_login varchar(20) not null,
usuario_password varchar(20) not null,
usuario_nombre varchar(20) not null,
usuario_apellido varchar(20) not null,
rol_id int(6) not null,
primary key (usuario_id),
unique (usuario_login),
foreign key (rol_id) references rol(rol_id) on update cascade on delete no action
);

Insert into usuario (usuario_login, usuario_password, usuario_nombre, usuario_apellido, rol_id) values
('maquispe', 'maquispe', 'María', 'Quispe', 1),
('anflores', 'anflores', 'Ana', 'Flores', 2),
('rosanchez', 'rosanchez', 'Rosa', 'Sánchez', 3);

/*		Tablas de Transacciones		*/

Create table solicitud
(
solicitud_id int(6) auto_increment not null,
solicitud_fecha datetime not null,
usuario_id int(6) not null,
estado_id int(6) not null,

solicitud_nombre varchar(20) not null,
solicitud_resumen varchar(500) not null,
normativa_id int(6) not null,
solicitud_file longblob not null,

tecnico_asignado int(6),

primary key (solicitud_id),
foreign key (estado_id) references estado(estado_id) on update cascade on delete no action,
foreign key (normativa_id) references normativa(normativa_id) on update cascade on delete no action,
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action,
foreign key (tecnico_asignado) references usuario(usuario_id) on update cascade on delete no action
);

Insert into solicitud values (null, '2020-07-13 00:00:00', 1, 1, 'Solicitud # 1', 'Solicitud en espera', 1, 'asda', null);
Insert into solicitud values (null, '2020-07-14 00:00:00', 1, 2, 'Solicitud # 2', 'Solicitud Aprobada', 1, 'asda', 1);
Insert into solicitud values (null, '2020-07-15 00:00:00', 1, 3, 'Solicitud # 3', 'Solicitud Rechazada', 1, 'asda', null);
Insert into solicitud values (null, '2020-07-15 00:00:00', 1, 4, 'Solicitud # 4', 'Solicitud Atendida', 1, 'asda', 1);
Insert into solicitud values (null, '2020-07-15 00:00:00', 1, 4, 'Solicitud # 5', 'Solicitud Finalizada', 1, 'asda', 1);

Create table informe
(
informe_id int(6) auto_increment not null,
informe_fecha datetime not null,

solicitud_id int(6) not null,
usuario_id int(6) not null,

informe_argumento varchar(500) not null,

primary key (informe_id),
foreign key (solicitud_id) references solicitud(solicitud_id) on update cascade on delete no action,
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action
);

Create table portal
(
portal_id int(6) auto_increment not null,
portal_fecha datetime not null,

solicitud_id int(6) not null,
usuario_id int(6) not null,
visibilidad int(1) default '1',

primary key (portal_id),
foreign key (solicitud_id) references solicitud(solicitud_id) on update cascade on delete no action,
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action
);

Create table menu
(
menu_id int(6) auto_increment not null,
menu_descripcion varchar(60) not null,
menu_url varchar(300) not null,
primary key (menu_id)
);

Insert into menu values(null, "Solicitudes Presentadas", "solicitudesPresentadas.jsp"), (null, "Solicitudes Pendientes", "solicitudesPendientes.jsp");

Create table acceso
(
menu_id int(6) not null,
usuario_id int(6) not null,
primary key (menu_id, usuario_id), key usuario_id(usuario_id),
foreign key (menu_id) references menu(menu_id),
foreign key (usuario_id) references usuario(usuario_id)
);

Insert into acceso values(1, 1), (2, 2);
