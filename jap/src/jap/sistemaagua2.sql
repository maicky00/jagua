/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     19/08/2015 10:43:33                          */
/*==============================================================*/


drop table if exists ASISTENCIA;

drop table if exists CORTE;

drop table if exists DETALLEFACTURA;

drop table if exists FACTURAS;

drop table if exists INSTITUCION;

drop table if exists INVENTARIO;

drop table if exists LOGIN;

drop table if exists MEDIDOR;

drop table if exists OTROSPAGOS;

drop table if exists PAGOSASISTENCIA;

drop table if exists PLANIFICACION;

drop table if exists TARIFAS;

drop table if exists USUARIOS;

/*==============================================================*/
/* Table: ASISTENCIA                                            */
/*==============================================================*/
create table ASISTENCIA
(
   IDASISTENCIA         int not null auto_increment,
   IDPLANIFICACION      int,
   IDMEDIDOR            int,
   ASISTENCIA           varchar(2),
   VALORMULTA           float(8,2),
   DESCRIPCION          varchar(40),
   OBSEVACION           varchar(10),
   primary key (IDASISTENCIA)
);

/*==============================================================*/
/* Table: CORTE                                                 */
/*==============================================================*/
create table CORTE
(
   IDCORTE              int not null auto_increment,
   CORTE                varchar(2),
   FECHA                date,
   OBSERVACION          text,
   MULTA                float(8,2),
   MORA                 int,
   primary key (IDCORTE)
);

/*==============================================================*/
/* Table: DETALLEFACTURA                                        */
/*==============================================================*/
create table DETALLEFACTURA
(
   IDDETALLEFAC         int not null auto_increment,
   IDTARIFAS            int,
   IDMEDIDOR            int,
   ANIOMES              varchar(10),
   MEDIDAANT            int,
   MEDIDAACT            int,
   CONSUMO              int,
   MEDEXCEDIDO          int,
   TAREXCEDIDO          float(8,2),
   SUBTOTAL             float(8,2),
   TOTAL                float(8,2),
   OBSERVACION          varchar(10),
   primary key (IDDETALLEFAC)
);

/*==============================================================*/
/* Table: FACTURAS                                              */
/*==============================================================*/
create table FACTURAS
(
   IDFACTURA            int not null auto_increment,
   IDDETALLEFAC         int,
   NUMFACTURA           int,
   FECHAEMISION         date,
   SUBTOTAL             float(8,2),
   IVA                  float(8,2),
   TOTAL                float(8,2),
   USUARIOACTUAL        text,
   primary key (IDFACTURA)
);

/*==============================================================*/
/* Table: INSTITUCION                                           */
/*==============================================================*/
create table INSTITUCION
(
   IDINSTITUCION        int not null auto_increment,
   NOMBREINST           text,
   DIRECCION            text,
   TELEFONO             varchar(10),
   EMAIL                varchar(60),
   RUC                  varchar(15),
   CELULAR              varchar(11),
   LOGO                 longblob,
   primary key (IDINSTITUCION)
);

/*==============================================================*/
/* Table: INVENTARIO                                            */
/*==============================================================*/
create table INVENTARIO
(
   IDINVENTARIO         int not null auto_increment,
   CODARTICULO          varchar(25),
   DESCRIPCION          text,
   FECHAADQUISICION     date,
   VALOR                float(8,2),
   DEPRECIABLE          varchar(6),
   NOMBRE               text,
   CANTIDAD             int,
   IMAGEN               longblob,
   primary key (IDINVENTARIO)
);

/*==============================================================*/
/* Table: LOGIN                                                 */
/*==============================================================*/
create table LOGIN
(
   IDLOGIN              int not null auto_increment,
   NOMBRES              varchar(80),
   APELLIDOS            varchar(80),
   CEDULA               varchar(15),
   USUARIO              varchar(50),
   CLAVE                varchar(50),
   TIPO                 varchar(30),
   ESTADO               varchar(10),
   primary key (IDLOGIN)
);

/*==============================================================*/
/* Table: MEDIDOR                                               */
/*==============================================================*/
create table MEDIDOR
(
   IDMEDIDOR            int not null auto_increment,
   IDUSUARIO            int,
   SERIE                varchar(20),
   NUMMEDIDOR           int,
   ESTADO               varchar(10),
   VALORPORCONEXION     float(8,2),
   primary key (IDMEDIDOR)
);

/*==============================================================*/
/* Table: OTROSPAGOS                                            */
/*==============================================================*/
create table OTROSPAGOS
(
   IDOTPAGOS            int not null auto_increment,
   IDCORTE              int,
   IDMEDIDOR            int,
   DERCONX              text,
   MULRECX              float(8,2),
   INTERES              float(8,2),
   TOTAL                float(8,2),
   OBSERVACION          varchar(10),
   USUARIOACTUAL        text,
   primary key (IDOTPAGOS)
);

/*==============================================================*/
/* Table: PAGOSASISTENCIA                                       */
/*==============================================================*/
create table PAGOSASISTENCIA
(
   IDPAGOASISTENCIA     int not null auto_increment,
   IDASISTENCIA         int,
   FECHAPAGO            date,
   NUMMINGAS            int,
   VALORMINGAS          float(8,2),
   OBSERVACION          varchar(20),
   USUARIOACTUAL        text,
   NUMFACTURA           int,
   primary key (IDPAGOASISTENCIA)
);

/*==============================================================*/
/* Table: PLANIFICACION                                         */
/*==============================================================*/
create table PLANIFICACION
(
   IDPLANIFICACION      int not null auto_increment,
   TIPOPLANIFICACION    varchar(20),
   LUGAR                text,
   FECHA                date,
   VALORMULTA           float(8,2),
   DESCRIPCION          text,
   primary key (IDPLANIFICACION)
);

/*==============================================================*/
/* Table: TARIFAS                                               */
/*==============================================================*/
create table TARIFAS
(
   IDTARIFAS            int not null auto_increment,
   BASE                 int,
   TARBASE              float(8,2),
   DESCRIPCION          text,
   VALOREXCESO          float(8,2),
   ALCANTARRILLADO      float(8,2),
   IVA                  float(8,2),
   primary key (IDTARIFAS)
);

/*==============================================================*/
/* Table: USUARIOS                                              */
/*==============================================================*/
create table USUARIOS
(
   IDUSUARIO            int not null auto_increment,
   IDINSTITUCION        int,
   RUCCI                varchar(15),
   PRIMERNOMBRE         varchar(50),
   SEGUNDONOMBRE        varchar(50),
   PRIMERAPELLIDO       varchar(50),
   SEGUNDOAPELLIDO      varchar(50),
   APADOSN              varchar(50),
   DIRECCION            text,
   TELEFONO             varchar(10),
   CELULAR              varchar(10),
   SECTOR               text,
   REFERENCIA           text,
   FOTO                 longblob,
   OBSERVACION          text,
   primary key (IDUSUARIO)
);

alter table ASISTENCIA add constraint FK_REFERENCE_15 foreign key (IDPLANIFICACION)
      references PLANIFICACION (IDPLANIFICACION) on delete restrict on update restrict;

alter table ASISTENCIA add constraint FK_REFERENCE_16 foreign key (IDMEDIDOR)
      references MEDIDOR (IDMEDIDOR) on delete restrict on update restrict;

alter table DETALLEFACTURA add constraint FK_REFERENCE_4 foreign key (IDTARIFAS)
      references TARIFAS (IDTARIFAS) on delete restrict on update restrict;

alter table DETALLEFACTURA add constraint FK_REFERENCE_7 foreign key (IDMEDIDOR)
      references MEDIDOR (IDMEDIDOR) on delete restrict on update restrict;

alter table FACTURAS add constraint FK_REFERENCE_10 foreign key (IDDETALLEFAC)
      references DETALLEFACTURA (IDDETALLEFAC) on delete restrict on update restrict;

alter table MEDIDOR add constraint FK_REFERENCE_1 foreign key (IDUSUARIO)
      references USUARIOS (IDUSUARIO) on delete restrict on update restrict;

alter table OTROSPAGOS add constraint FK_REFERENCE_11 foreign key (IDCORTE)
      references CORTE (IDCORTE) on delete restrict on update restrict;

alter table OTROSPAGOS add constraint FK_REFERENCE_12 foreign key (IDMEDIDOR)
      references MEDIDOR (IDMEDIDOR) on delete restrict on update restrict;

alter table PAGOSASISTENCIA add constraint FK_REFERENCE_17 foreign key (IDASISTENCIA)
      references ASISTENCIA (IDASISTENCIA) on delete restrict on update restrict;

alter table USUARIOS add constraint FK_REFERENCE_9 foreign key (IDINSTITUCION)
      references INSTITUCION (IDINSTITUCION) on delete restrict on update restrict;

