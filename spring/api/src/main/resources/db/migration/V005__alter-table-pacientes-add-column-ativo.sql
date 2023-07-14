alter table tb_pacientes add column ativo tinyint;
update tb_pacientes set ativo = 1;
alter table tb_pacientes modify ativo tinyint not null;