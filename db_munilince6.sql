Drop database munilince6;

Create database if not exists munilince6 default character set utf8;
Use munilince6;

/*		Tablas Independientes		*/

Create table visibilidad
(
visibilidad_id int(6) auto_increment not null,
visibilidad_nombre varchar(20) not null,
primary key (visibilidad_id)
);

Insert into visibilidad (visibilidad_nombre) values ('Visible'),('Oculto');

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
('maquispe', 'maquispe', 'María', 'Quispe', 1), # Unidad Orgánica
('anflores', 'anflores', 'Ana', 'Flores', 2), # Subgerente
('rosanchez', 'rosanchez', 'Rosa', 'Sánchez', 3); # Técnico Especialista

/*		Tablas de Transacciones		*/

Create table solicitud
(
solicitud_id int(6) auto_increment not null,
solicitud_fecha datetime not null,
estado_id int(6) not null,
normativa_id int(6) not null,
primary key (solicitud_id),
foreign key (estado_id) references estado(estado_id) on update cascade on delete no action,
foreign key (normativa_id) references normativa(normativa_id) on update cascade on delete no action
);

Create table detallesolicitud
(
solicitud_id int(6) not null,
usuario_id int(6) not null,
solicitud_nombre varchar(20) not null,
solicitud_resumen varchar(250) not null,
solicitud_file blob not null,
primary key (solicitud_id, usuario_id),
foreign key (solicitud_id) references solicitud(solicitud_id) on update cascade on delete no action,
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action
);

Create table informe
(
informe_id int(6) auto_increment not null,
informe_fecha datetime not null,
usuario_id int(6) not null,
primary key (informe_id),
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action
);

Create table detalleinforme
(
informe_id int(6) auto_increment not null,
solicitud_id int(6) not null,
informe_argumento varchar(250) not null,
primary key (informe_id, solicitud_id),
foreign key (solicitud_id) references solicitud(solicitud_id) on update cascade on delete no action,
foreign key (informe_id) references informe(informe_id) on update cascade on delete no action
);

Create table portal
(
portal_id int(6) auto_increment not null,
solicitud_id int(6) not null,
visibilidad_id int(6) not null,
portal_fecha datetime not null,
usuario_id int(6) not null,
primary key (portal_id),
foreign key (solicitud_id) references solicitud(solicitud_id) on update cascade on delete no action,
foreign key (visibilidad_id) references visibilidad(visibilidad_id) on update cascade on delete no action,
foreign key (usuario_id) references usuario(usuario_id) on update cascade on delete no action
);
