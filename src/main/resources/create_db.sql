-- создать обычного пользователя:
CREATE USER ucatalog PASSWORD 'qmos2wez';

-- *******************  authentication *************************
-- Создайте БД authentication:
CREATE DATABASE catalog_db WITH OWNER = ucatalog ENCODING = 'UTF8';


GRANT ALL PRIVILEGES ON DATABASE "catalog_db" to ucatalog;


Или (если не сработает) так мы добавим все права на использование всех таблиц в базе database1 учетной записи dmosk:

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA sh_lema TO "qmos2wez";

-- переключиться на DB "authentication" (в консоли можно следующей командой, в админке вручную):
\c catalog_db
-- в последней версии необходимо зайти под пользователем tacouser

--Создайте схемы в БД authentication и дайте все права на нее пользователю tacouser:

CREATE SCHEMA IF NOT EXISTS catalog_sh;
GRANT ALL ON SCHEMA catalog_sh TO ucatalog;

