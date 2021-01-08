SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS `enchantmentsenhance`
(
    `playername` VARCHAR
(
    30
) NOT NULL UNIQUE,
    `failstack` INT NOT NULL DEFAULT 0,
    `items` text NOT NULL,
    `valks` text NOT NULL,
    `grind` INT DEFAULT 2,
    PRIMARY KEY
(
    `playername`
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

