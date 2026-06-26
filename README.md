Banco de Dados:

ALUNOS: LUIZ GABRIEL LIMA DE SOUZA && DAVI PALHARES DE BARROS VILARIM


-- ============================================================
--  NewDayNewAnomaly – Script de criação do banco de dados
--  Execute como superusuário PostgreSQL:
--    psql -U postgres -f create_database.sql
-- ============================================================


-- 3. Criar o schema do jogo
CREATE SCHEMA IF NOT EXISTS game;

-- 4. Tabela principal: status do jogador por dia
CREATE TABLE IF NOT EXISTS game.player_status (
    id              SERIAL          PRIMARY KEY,
    current_day     INTEGER         NOT NULL DEFAULT 1,
    money           INTEGER         NOT NULL DEFAULT 0,
    sanity          REAL            NOT NULL DEFAULT 100.0,
    anomalies_guessed INTEGER       NOT NULL DEFAULT 0,
    humans_guessed  INTEGER         NOT NULL DEFAULT 0,
    cola            INTEGER         NOT NULL DEFAULT 0,
    big_cola        INTEGER         NOT NULL DEFAULT 0,
    chips           INTEGER         NOT NULL DEFAULT 0,
    glasses         BOOLEAN         NOT NULL DEFAULT FALSE,
    saved_at        TIMESTAMP       NOT NULL DEFAULT NOW()
);

-- 5. Comentários descritivos
COMMENT ON TABLE  game.player_status             IS 'Histórico de saves do jogador por dia';
COMMENT ON COLUMN game.player_status.current_day IS 'Dia atual do jogo ao salvar';
COMMENT ON COLUMN game.player_status.money       IS 'Dinheiro disponível';
COMMENT ON COLUMN game.player_status.sanity      IS 'Sanidade do jogador (0–100)';
COMMENT ON COLUMN game.player_status.anomalies_guessed IS 'Total de anomalias identificadas corretamente';
COMMENT ON COLUMN game.player_status.humans_guessed    IS 'Total de humanos identificados corretamente';
COMMENT ON COLUMN game.player_status.cola        IS 'Quantidade de CocaCola pequena no inventário';
COMMENT ON COLUMN game.player_status.big_cola    IS 'Quantidade de CocaCola grande no inventário';
COMMENT ON COLUMN game.player_status.chips       IS 'Quantidade de Chips no inventário';
COMMENT ON COLUMN game.player_status.glasses     IS 'O jogador possui os óculos?';
COMMENT ON COLUMN game.player_status.saved_at    IS 'Timestamp do momento em que o save foi feito';
