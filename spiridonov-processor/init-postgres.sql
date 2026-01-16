CREATE TABLE IF NOT EXISTS сырые_события_доставки (
    идентификатор SERIAL PRIMARY KEY,
    отправитель VARCHAR(255),
    получатель VARCHAR(255),
    адрес_доставки TEXT,
    статус VARCHAR(100),
    дата_события TIMESTAMP
);
