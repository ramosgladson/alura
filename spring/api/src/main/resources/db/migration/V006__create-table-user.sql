create table tb_user(
    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)
);

insert into tb_user values(null, 'admin', '$2y$10$AD.TixFGQSmqeoB3ZYlCWOgUo2Y1kX2CfRR/inRzTm0uTfw3DS2F.');