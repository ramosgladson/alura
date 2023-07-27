ALTER table tb_consultas add column ativo tinyint;
update tb_consultas set ativo = 1;
ALTER table tb_consultas modify ativo tinyint not null;
ALTER table tb_consultas add column motivo_cancelamento varchar(255);

