alter table tb_medicos add column ativo tinyint;
update tb_medicos set ativo = 1;
alter table tb_medicos modify ativo tinyint not null;