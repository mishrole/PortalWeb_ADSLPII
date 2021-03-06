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

Insert into rol (rol_nombre) values ('Unidad Orgánica'),('Subgerente'),('Técnico Especialista'),('Administrador');

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

Insert into usuario (usuario_id, usuario_login, usuario_password, usuario_nombre, usuario_apellido, rol_id) values
(1, 'maquispe', 'maquispe', 'María', 'Quispe', 1),
(2, 'anflores', 'anflores', 'Ana', 'Flores', 2),
(3, 'rosanchez', 'rosanchez', 'Rosa', 'Sánchez', 3),
(4, 'mishrole', 'mishrole', 'Mitchell', 'Rodríguez', 4),
(5, 'test', 'test', 'Test', 'Especialista', 3);

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

Insert into solicitud values (null, '2020-07-13 01:00:00', 1, 1, 'Solicitud # 1', 'Solicitud # 1', 1, 'FILEPDF1', null);
Insert into solicitud values (null, '2020-07-14 02:00:00', 1, 1, 'Solicitud # 2', 'Solicitud # 2', 2, 'FILEPDF2', null);
Insert into solicitud values (null, '2020-07-15 03:00:00', 1, 1, 'Solicitud # 3', 'Solicitud # 3', 3, 'FILEPDF3', null);
Insert into solicitud values (null, '2020-07-16 04:00:00', 1, 1, 'Solicitud # 4', 'Solicitud # 4', 4, 'FILEPDF4', null);
Insert into solicitud values (null, '2020-07-17 05:00:00', 1, 1, 'Solicitud # 5', 'Solicitud # 5', 1, 'FILEPDF5', null);

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

Create table acceso
(
menu_id int(6) not null,
usuario_id int(6) not null,
primary key (menu_id, usuario_id), key usuario_id(usuario_id),
foreign key (menu_id) references menu(menu_id),
foreign key (usuario_id) references usuario(usuario_id)
);

/*	Portal Web (Lista) es Global -> home.jsp	*/
Insert into menu values(1, "Solicitudes Presentadas", "solicitudesPresentadas.jsp"); /*	Unidad Orgánica 1 */
Insert into menu values(2, "Solicitudes", "solicitudesPendientes.jsp"), (3, "Informes", "informesPendientes.jsp"); /*	Subgerente 2	*/
Insert into menu values(4, "Publicaciones", "publicacionesPendientes.jsp");  /*	Técnico Especialista 3	*/
Insert into menu values(5, "Usuario", "usuario.jsp"), (6, "Rol", "rol.jsp"), (7, "Estado", "estado.jsp"), (8, "Normativa", "normativa.jsp"); /*	Administrador 4	*/

Insert into acceso values(1, 1);
Insert into acceso values(2, 2), (3, 2);
Insert into acceso values(4, 3);
Insert into acceso values(5, 4), (6, 4), (7, 4), (8, 4);
Insert into acceso values(4, 5);
