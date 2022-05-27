create schema if not exists projeto_integrador_vi;

create table if not exists projeto_integrador_vi.produto(
    id serial primary key,
    descricao varchar(100) not null,
    valor_venda numeric (7,2) not null,
    quantidade_kg numeric(7,2) not null default 0.00,
    estoque_minimo_kg numeric (7,2),
    ativo char default 'S' check(ativo in ('S', 'N'))
);

create table if not exists projeto_integrador_vi.fornecedor(
    id serial primary key,
    nome varchar(100) not null,
    cnpj varchar(14) not null,
    fone varchar(11) not null,
    email varchar(50),
    endereco varchar(200) not null,
    ativo char default 'S' check(ativo in ('S', 'N'))
);

create table if not exists projeto_integrador_vi.compra(
    id serial primary key,
    dt timestamp not null default current_timestamp,
    fornecedor_id int not null,
    valor_total numeric (8,2),
    recebido char default 'N' check(recebido in ('S','N')),
    cancelado char default 'N' check(cancelado in ('S','N')),
    foreign key (fornecedor_id) references projeto_integrador_vi.fornecedor(id)
);
    
create table if not exists projeto_integrador_vi.compra_produto (
    id serial primary key,
    compra_id int not null,
    produto_id int not null,
    quantidade_kg numeric(7,2) not null,
    valor_kg numeric(7,2) not null,
    valor_total numeric(8,2) not null,
    foreign key (compra_id) references projeto_integrador_vi.compra(id),
    foreign key (produto_id) references projeto_integrador_vi.produto(id)
);    
    
create table if not exists projeto_integrador_vi.deposito(
    id serial primary key,
    descricao varchar(100) not null,
    endereco varchar(200) not null,
    ativo char default 'S' check(ativo in ('S','N'))
);

create table if not exists projeto_integrador_vi.deposito_area(
    id serial primary key,
    descricao varchar(100) not null,
    deposito_id int not null,
    limite_kg numeric(7,2),
    atual_kg numeric(7,2) not null default 00000.00,
    disponivel char default 'S' check(ativo in ('S','N')),
    ativo char default 'S' check(ativo in ('S','N')),
    foreign key (deposito_id) references projeto_integrador_vi.deposito(id)
);

create table if not exists projeto_integrador_vi.produto_detalhes(
    id serial primary key,
    produto_id int not null,
    area_id int null,
    quantidade_kg numeric (7,2) not null default 00000.00,
    validade date not null,
    foreign key(produto_id) references projeto_integrador_vi.produto(id),
    foreign key(area_id) references projeto_integrador_vi.deposito_area(id));
    
create table if not exists projeto_integrador_vi.cliente(
    id serial primary key,
    nome varchar(100) not null,
    cnpj varchar(14) not null,
    fone varchar(11) not null,
    email varchar(50),
    endereco varchar(200) not null,
    ativo char default 'S' check(ativo in ('S', 'N'))
);

create table if not exists projeto_integrador_vi.venda(
    id serial primary key,
    dt timestamp not null default current_timestamp,
    cliente_id int not null,
    valor_total numeric (8,2),
    entregue char default 'N' check(entregue in ('S','N')),
    cancelado char default 'N' check(cancelado in ('S','N')),
    foreign key (cliente_id) references projeto_integrador_vi.cliente(id)
);

create table if not exists projeto_integrador_vi.venda_produto (
    id serial primary key,
    venda_id int not null,
    produto_id int not null,
    quantidade_kg numeric(7,2) not null,
    valor_kg numeric(7,2) not null,
    valor_total numeric(8,2) not null,
    foreign key (venda_id) references projeto_integrador_vi.venda(id),
    foreign key (produto_id) references projeto_integrador_vi.produto(id)
);

create table if not exists projeto_integrador_vi.alerta_validade(
    id serial primary key,
    dt_alerta date not null default current_date,
    produto_detalhes_id int not null,
    ativo char default 'S' check(ativo in ('S', 'N')),
    foreign key (produto_detalhes_id) references projeto_integrador_vi.produto_detalhes(id)
);

create table if not exists projeto_integrador_vi.alerta_estoque_minimo(
    id serial primary key,
    produto_id int not null,
    quantidade_kg numeric(7,2) not null,
    ativo char default 'S' check(ativo in ('S', 'N')),
    foreign key (produto_id) references projeto_integrador_vi.produto(id)
);

