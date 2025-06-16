CREATE DATABASE IF NOT EXISTS car_rental
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE car_rental;

CREATE TABLE Клиент (
    id INT AUTO_INCREMENT PRIMARY KEY,
    имя VARCHAR(50) NOT NULL,
    фамилия VARCHAR(50) NOT NULL,
    отчество VARCHAR(50),
    адрес VARCHAR(100),
    телефон VARCHAR(20)
);

CREATE TABLE Автомобиль (
    id_автомобиля INT AUTO_INCREMENT PRIMARY KEY,
    марка VARCHAR(50) NOT NULL,
    тип VARCHAR(50),
    год_выпуска INT,
    стоимость_в_сутки DECIMAL(10,2) NOT NULL
);

CREATE TABLE Скидка (
    id_скидки INT AUTO_INCREMENT PRIMARY KEY,
    значение DECIMAL(5,2) NOT NULL,
    описание TEXT
);

CREATE TABLE Штраф (
    id_штрафа INT AUTO_INCREMENT PRIMARY KEY,
    значение DECIMAL(5,2) NOT NULL,
    описание TEXT
);

CREATE TABLE Прокат (
    id_проката INT AUTO_INCREMENT PRIMARY KEY,
    дата_выдачи DATE NOT NULL,
    ожидаемая_дата_возврата DATE NOT NULL,
    стоимость_проката DECIMAL(10,2) NOT NULL,
    id_клиента INT NOT NULL,
    id_автомобиля INT NOT NULL,
    id_скидки INT,
    id_штрафа INT,
    FOREIGN KEY (id_клиента) REFERENCES Клиент(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_автомобиля) REFERENCES Автомобиль(id_автомобиля)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_скидки) REFERENCES Скидка(id_скидки)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (id_штрафа) REFERENCES Штраф(id_штрафа)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
