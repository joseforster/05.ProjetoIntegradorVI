insert into projeto_integrador_vi.fornecedor values (
    default,
    'José',
    '71748758000150',
    '51997956687',
    'jose@univates.com',
    'Santa Cruz do Sul, RS',
    default
);

insert into projeto_integrador_vi.fornecedor values (
    default,
    'João',
    '01334610000194',
    '51997756654',
    'joao@univates.com',
    'Porto Alegre, RS',
    default
);

insert into projeto_integrador_vi.produto values (
    default,
    'Arroz 1KG',
    11.99,
    500,
    default
);

insert into projeto_integrador_vi.produto values (
    default,
    'Filé 5KG',
    120.99,
    55,
    default
);

insert into projeto_integrador_vi.deposito values (
    default,
    'Depósito de Santa Cruz',
    'Santa Cruz do Sul, RS'
);

insert into projeto_integrador_vi.deposito values (
    default,
    'Depósito de Lajeado',
    'Lajeado, RS'
);

insert into projeto_integrador_vi.deposito_area values(
    default,
    'Área A',
    1,
    500,
    default
);

insert into projeto_integrador_vi.cliente values (
    default,
    'Mercadinho do Moisés',
    '95300571000158',
    '51987456321',
    'mercadinho@univates.com',
    'Canoas, RS',
    default
);

insert into projeto_integrador_vi.produto_detalhes values (
    default,
    1,
    1,
    32,
    '01/11/2023'
);

insert into projeto_integrador_vi.produto_detalhes values (
    default,
    2,
    1,
    56,
    '31/12/2023'
);


insert into projeto_integrador_vi.compra values
(default, default,null, 2, 'N', 'N');

insert into projeto_integrador_vi.compra values
(default, default,current_date, 1, 'S', 'N');

insert into projeto_integrador_vi.compra_produto values
(default, 1,1, 10, 2.2, 22.00);

insert into projeto_integrador_vi.compra_produto values
(default, 2,2, 10, 3, 30.00);

insert into projeto_integrador_vi.venda values
(default, default,1, 'N');

insert into projeto_integrador_vi.venda_produto values
(default, 1,1, 10, 3, 30.00);


insert into projeto_integrador_vi.produto_detalhes values (
    default,
    2,
    1,
    31,
    '07/07/2022'
);
