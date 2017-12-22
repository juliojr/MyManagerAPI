create table cabecalho (
        id bigint not null auto_increment,
        data_criacao datetime not null,
        data_movimento datetime not null,
        tipo varchar(255) not null,
        integrante_id bigint,
        usuario_id bigint,
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table integrante (
        id bigint not null auto_increment,
        bairro varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255) not null,
        cpf_cnpj varchar(255) not null,
        data_criacao datetime not null,
        ddd integer not null,
        nome varchar(255) not null,
        numero integer not null,
        rua varchar(255) not null,
        telefone bigint not null,
        uf varchar(255) not null,
        usuario_id bigint,
        primary key (id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table item (
        id bigint not null auto_increment,
        data_criacao datetime not null,
        data_pagamento datetime not null,
        pago bit not null,
        quantidade double precision not null,
        unitario double precision not null,
        cabecalho_id bigint,
        produto_id bigint,
        usuario_id bigint,
        primary key (id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table produto (
        id bigint not null auto_increment,
        caminho_foto varchar(255),
        data_criacao datetime not null,
        descricao varchar(255) not null,
        usuario_id bigint,
        primary key (id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table usuario (
        id bigint not null auto_increment,
        data_criacao datetime not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

    alter table cabecalho 
        add constraint FKtlxjqpaae13aox6uxfyjggjey 
        foreign key (integrante_id) 
        references integrante (id);

    alter table cabecalho 
        add constraint FKadmuvd8oght7jvcqrfr9h7ukq 
        foreign key (usuario_id) 
        references usuario (id);

    alter table integrante 
        add constraint FKit9brlnwpiy8oxy4x2nbd4ead 
        foreign key (usuario_id) 
        references usuario (id);

    alter table item 
        add constraint FK6baintp9oukq4wl3q1cdyy51h 
        foreign key (cabecalho_id) 
        references cabecalho (id);

    alter table item 
        add constraint FKoya2x5ip1q2t3s0936vgjiyx9 
        foreign key (produto_id) 
        references produto (id);

    alter table item 
        add constraint FKpmv00mpj3ufvwmkh24x4j6j65 
        foreign key (usuario_id) 
        references usuario (id);

    alter table produto 
        add constraint FK4qqkq7cmomd87obw16prilngw 
        foreign key (usuario_id) 
        references usuario (id);
  
