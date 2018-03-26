/* forcei manualmente no banco
--criação das tabelas
create table usuario (
  id bigint(20) not null auto_increment,
  data_criacao datetime not null,
  email varchar(255) not null,
  nome varchar(255) not null,
  senha varchar(255) not null,
  primary key (id),
  unique key email_unique (email)
) engine=innodb auto_increment=1 default charset=utf8;

create table integrante (
  id bigint(20) not null auto_increment,
  bairro varchar(255) not null,
  cidade varchar(255) not null,
  complemento varchar(255) not null,
  cpf_cnpj varchar(255) not null,
  data_criacao datetime not null,
  ddd int(11) not null,
  nome varchar(255) not null,
  numero int(11) not null,
  rua varchar(255) not null,
  telefone varchar(20) not null,
  uf varchar(255) not null,
  usuario_id bigint(20) default null,
  tipo_integrante varchar(255),
  primary key (id),
  key fk_integrante_usuario (usuario_id),
  constraint fk_integrante_usuario foreign key (usuario_id) references usuario (id)
) engine=innodb auto_increment=1 default charset=utf8;

create table produto (
  id bigint(20) not null auto_increment,
  caminho_foto varchar(255) default null,
  data_criacao datetime not null,
  descricao varchar(255) not null,
  usuario_id bigint(20) default null,
  primary key (id),
  key fk_produto_usuario (usuario_id),
  constraint fk_produto_usuario foreign key (usuario_id) references usuario (id)
) engine=innodb auto_increment=1 default charset=utf8;

create table cabecalho (
  id bigint(20) not null auto_increment,
  data_criacao datetime not null,
  tipo varchar(255) not null,
  integrante_id bigint(20) default null,
  usuario_id bigint(20) default null,
  primary key (id),
  key fk_cabecalho_usuario (usuario_id),
  key fk_cabecalho_integrante (integrante_id),
  constraint fk_cabecalho_usuario foreign key (usuario_id) references usuario (id),
  constraint fk_cabecalho_integrante foreign key (integrante_id) references integrante (id)
) engine=innodb auto_increment=1 default charset=utf8;

create table item (
  id bigint(20) not null auto_increment,
  data_criacao datetime not null,
  data_pagamento date not null,
  situacao varchar(255) not null,
  quantidade double not null,
  unitario double not null,
  cabecalho_id bigint(20) default null,
  produto_id bigint(20) default null,
  usuario_id bigint(20) default null,
  primary key (id),
  key fk_item_cabecalho (cabecalho_id),
  key fk_item_produto (produto_id),
  key fk_item_usuario (usuario_id),
  constraint fk_item_cabecalho foreign key (cabecalho_id) references cabecalho (id),
  constraint fk_item_produto foreign key (produto_id) references produto (id),
  constraint fk_item_usuario foreign key (usuario_id) references usuario (id)
) engine=innodb auto_increment=1 default charset=utf8;

  
--criação das views
create or replace view view_geral as 
select uuid_short() as id,
       c.usuario_id as usuario_id,
       c.data_criacao as cabecalho_data_criacao,
       c.integrante_id as integrante_id,
       c.tipo as tipo,
       i.cabecalho_id as cabecalho_id,
       i.data_criacao as item_data_criacao,
       i.data_pagamento as data_pagamento,
       i.id as item_id,
       i.produto_id as produto_id,
       i.quantidade as quantidade,
       i.situacao as situacao,
       i.unitario as unitario,
       (i.quantidade * i.unitario) as valor_total,
       p.data_criacao as produto_data_criacao,
       p.caminho_foto as caminho_foto,
       p.descricao as descricao,
       it.uf as uf,
       it.cidade as cidade,
       it.bairro as bairro,
       it.rua as rua,
       it.numero as numero,
       it.complemento as complemento,
       it.cpf_cnpj as cpf_cnpj,
       it.data_criacao as integrante_data_criacao,
       it.ddd as ddd,
       it.telefone as telefone,
       it.tipo_integrante,
       u.data_criacao as usuario_data_criacao,
       u.email as email,
       u.nome as nome,
       dayofmonth(i.data_pagamento) as dia,
       month(i.data_pagamento) as mes,
       year(i.data_pagamento) as ano
  from ((((usuario u join cabecalho c on((u.id = c.usuario_id))) join item i on((c.id = i.cabecalho_id))) join
        integrante it on((c.integrante_id = it.id))) join produto p on((i.produto_id = p.id)));

create or replace view balanco_mensal as 
select uuid_short() as id,
       a.situacao as situacao,
       a.usuario_id as usuario_id,
       a.ano as ano,
       a.mes as mes,
       a.compra as compra,
       a.venda as venda,
       a.geral as geral
  from (select 'GERAL' as situacao,
               v.usuario_id as usuario_id,
               v.ano as ano,
               v.mes as mes,
               sum(if((v.tipo = 'COMPRA'), v.valor_total, 0)) as compra,
               sum(if((v.tipo = 'VENDA'), v.valor_total, 0)) as venda,
               sum(if((v.tipo = 'VENDA'), v.valor_total, (v.valor_total * - (1)))) as geral
          from view_geral v
         group by v.usuario_id, v.ano, v.mes
        union all
        select v.situacao as situacao,
               v.usuario_id as usuario_id,
               v.ano as ano,
               v.mes as mes,
               sum(if((v.tipo = 'COMPRA'), v.valor_total, 0)) as compra,
               sum(if((v.tipo = 'VENDA'), v.valor_total, 0)) as venda,
               sum(if((v.tipo = 'VENDA'), v.valor_total, (v.valor_total * - (1)))) as geral
          from view_geral v
         group by v.situacao, v.usuario_id, v.ano, v.mes) a;

create or replace view fechamento_dia as 
select uuid_short() as id,
       a.usuario_id as usuario_id,
       a.data_pagamento as data_pagamento,
       a.ano as ano,
       a.mes as mes,
       a.dia as dia,
       a.situacao as situacao,
       a.valor_total as valor_total
  from (select distinct v.usuario_id as usuario_id,
                        v.data_pagamento as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        v.dia as dia,
                        v.situacao as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and (vv.situacao = v.situacao) and
                                (vv.data_pagamento <= v.data_pagamento) and (vv.situacao = 'PAGO'))) as valor_total
          from view_geral v
         where (v.situacao = 'PAGO')
        union
        select distinct v.usuario_id as usuario_id,
                        v.data_pagamento as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        v.dia as dia,
                        'GERAL' as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and (vv.data_pagamento <= v.data_pagamento))) as valor_total
          from view_geral v
        union
        select distinct v.usuario_id as usuario_id,
                        last_day(v.data_pagamento) as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        dayofmonth(last_day(v.data_pagamento)) as dia,
                        v.situacao as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and (vv.data_pagamento <= last_day(v.data_pagamento)) and
                                (vv.situacao = 'PAGO'))) as valor_total
          from view_geral v
         where (v.situacao = 'PAGO')
        union
        select distinct v.usuario_id as usuario_id,
                        str_to_date(concat_ws(',', 1, v.mes, v.ano), '%d,%m,%y') as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        1 as dia,
                        v.situacao as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and
                                (vv.data_pagamento <= str_to_date(concat_ws(',', 1, v.mes, v.ano), '%d,%m,%y')) and
                                (vv.situacao = 'PAGO'))) as valor_total
          from view_geral v
         where (v.situacao = 'PAGO')
        union
        select distinct v.usuario_id as usuario_id,
                        last_day(v.data_pagamento) as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        dayofmonth(last_day(v.data_pagamento)) as dia,
                        'GERAL' as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and (vv.data_pagamento <= last_day(v.data_pagamento)))) as valor_total
          from view_geral v
        union
        select distinct v.usuario_id as usuario_id,
                        str_to_date(concat_ws(',', 1, v.mes, v.ano), '%d,%m,%y') as data_pagamento,
                        v.ano as ano,
                        v.mes as mes,
                        1 as dia,
                        'GERAL' as situacao,
                        (select sum(if((vv.tipo = 'VENDA'), vv.valor_total, (vv.valor_total * - (1)))) as valor_total
                           from view_geral vv
                          where ((vv.usuario_id = v.usuario_id) and
                                (vv.data_pagamento <= str_to_date(concat_ws(',', 1, v.mes, v.ano), '%d,%m,%y')))) as valor_total
          from view_geral v) a
 order by a.usuario_id, a.situacao, a.data_pagamento;

create or replace view itens_pendentes as 
select g.id                     as id,
       g.usuario_id             as usuario_id,
       g.integrante_id          as integrante_id,
       g.cpf_cnpj               as cpf_cnpj,
       g.nome                   as nome,
       g.cabecalho_id           as cabecalho_id,
       g.cabecalho_data_criacao as cabecalho_data_criacao,
       g.tipo                   as tipo,
       g.item_id                as item_id,
       g.item_data_criacao      as item_data_criacao,
       g.data_pagamento         as data_pagamento,
       g.produto_id             as produto_id,
       g.descricao              as descricao,
       g.quantidade             as quantidade,
       g.unitario               as unitario,
       g.valor_total            as valor_total
  from view_geral g
 where ((g.data_pagamento <= sysdate()) and (g.situacao = 'ABERTO'))
 order by g.item_data_criacao desc, g.cabecalho_id, g.item_id;

create or replace view mais_integrantes as 
select uuid_short() as id,
       v.usuario_id as usuario_id,
       v.mes as mes,
       v.ano as ano,
       v.tipo as tipo,
       v.integrante_id as integrante_id,
       v.cpf_cnpj as cpf_cnpj,
       v.nome as nome,
       sum(v.quantidade) as quantidade,
       sum(v.valor_total) as valor_total
  from view_geral v
 group by v.usuario_id, v.mes, v.ano, v.tipo, v.integrante_id, v.cpf_cnpj, v.nome
 order by v.usuario_id, v.mes, v.ano, v.tipo, v.integrante_id, v.cpf_cnpj, v.nome, sum(v.valor_total) desc;

create or replace view mais_produtos as 
select uuid_short() as id,
       v.usuario_id as usuario_id,
       v.mes as mes,
       v.ano as ano,
       v.tipo as tipo,
       v.produto_id as produto_id,
       v.descricao as descricao,
       sum(v.quantidade) as quantidade,
       sum(v.valor_total) as valor_total
  from view_geral v
 group by v.usuario_id, month(v.data_pagamento), year(v.data_pagamento), v.tipo, v.produto_id, v.descricao
 order by v.usuario_id, year(v.data_pagamento), month(v.data_pagamento), sum(v.valor_total) desc;

create or replace view saldo_produtos as 
select uuid_short() as id,
       v.usuario_id as usuario_id,
       v.produto_id as produto_id,
       v.descricao as descricao,
       v.caminho_foto as caminho_foto,
       sum(if((v.tipo = 'COMPRA'), v.quantidade, (v.quantidade * - (1)))) as saldo
  from view_geral v
 group by v.usuario_id, v.produto_id, v.descricao, v.caminho_foto
 order by v.usuario_id, v.produto_id;


*/