/*tag1.0 start*/
/*==============================================================*/
/* Table: sys_attachment                                        */
/*==============================================================*/
create table sys_attachment
(
   id                   char(32) not null,
   id_owner             char(32) comment '该附件归属于那个业务数据的',
   path                 varchar(200),
   serverFilename       varchar(200),
   sourceFilename       varchar(200),
   fileNum              int,
   fileSize             varchar(50),
   fileKind             varchar(20),
   status               char(1) comment '0:临时文件（表单未入库）
            1:正在使用
            2:已删除',
   configCode           varchar(50),
   ft                   char(19) comment 'first Time',
   lt                   char(19) comment 'last Time',
   fu                   char(32) comment 'First user',
   lu                   char(32) comment 'last user'
);

alter table sys_attachment
   add primary key (id);

/*==============================================================*/
/* Table: sys_attachmentconfig                                  */
/*==============================================================*/
create table sys_attachmentconfig
(
   id                   char(32) not null,
   code                 varchar(50),
   saveDir              varchar(200),
   dirLevel             int,
   fileSize             int,
   fileCount            int,
   extension            varchar(200),
   description          varchar(200),
   ft                   char(19) comment 'first Time',
   lt                   char(19) comment 'last Time',
   fu                   char(32) comment 'First user',
   lu                   char(32) comment 'last user'
);

alter table sys_attachmentconfig
   add primary key (id);

/*==============================================================*/
/* Table: sys_attachmentsetting                                 */
/*==============================================================*/
create table sys_attachmentsetting
(
   id                   char(32) not null,
   limitFileExtention   varchar(500),
   ft                   char(19) comment 'first Time',
   lt                   char(19) comment 'last Time',
   fu                   char(32) comment 'First user',
   lu                   char(32) comment 'last user',
   type                 char(1) comment '0:本地 1:ftp',
   ftpAddress           varchar(500),
   ftpPort              varchar(20),
   ftpPath              varchar(1000),
   ftpUserName          varchar(100),
   ftpPassword          varchar(100)
);

alter table sys_attachmentsetting
   add primary key (id);

/*==============================================================*/
/* Table: sys_dictionary                                        */
/*==============================================================*/
create table sys_dictionary
(
   id                   char(32) not null,
   name                 varchar(200),
   code                 varchar(20),
   description          varchar(200),
   kind                 char(1) comment '0-普通
            1-树形
            ',
   enableFlag           char(1),
   editableFlag         char(1),
   systemFlag           char(1) comment '标记是否为系统初始化所需字典，系统字典用户不可编辑，只可查看。',
   ft                   char(19) comment 'first Time',
   lt                   char(19) comment 'last Time',
   fu                   char(32) comment 'First user',
   lu                   char(32) comment 'last user'
);

alter table sys_dictionary
   add primary key (id);

/*==============================================================*/
/* Table: sys_dictionaryvalue                                   */
/*==============================================================*/
create table sys_dictionaryvalue
(
   id                   char(32) not null,
   id_dictionary        char(32),
   id_parent            char(32),
   code                 varchar(20),
   value                varchar(50),
   valueI18n            varchar(200),
   num                  int,
   description          varchar(200),
   enableFlag           char(1),
   editableFlag         char(1),
   systemFlag           char(1),
   ft                   char(19) comment 'first Time',
   lt                   char(19) comment 'last Time',
   fu                   char(32) comment 'First user',
   lu                   char(32) comment 'last user'
);

alter table sys_dictionaryvalue
   add primary key (id);

alter table sys_dictionaryvalue add constraint FK_SYS_DICTIONARYVALUE_DICTIONARY foreign key (id_dictionary)
      references sys_dictionary (id);

alter table sys_dictionaryvalue add constraint FK_SYS_DICTIONARYVALUE_PARENT foreign key (id_parent)
      references sys_dictionaryvalue (id);
/*tag1.0 end*/
