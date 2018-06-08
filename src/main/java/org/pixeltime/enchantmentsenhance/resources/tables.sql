SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS `sw_player` (
  `player_id`    INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `player_name`  		VARCHAR(255)    NOT NULL UNIQUE,
  `failstack`        INT(6)          NOT NULL DEFAULT 0,
  `advice_of_valks`	VARCHAR(255)    NOT NULL,
  `stone1`      INT(6)          NOT NULL DEFAULT 0,
  `stone2`       INT(6)          NOT NULL DEFAULT 0,
  `stone3`      INT(6)          NOT NULL DEFAULT 0,
  `stone4`         INT(6)          NOT NULL DEFAULT 0,
  PRIMARY KEY (`player_id`),
  KEY (`player_name`)
)

  ENGINE =InnoDB
  DEFAULT CHARSET =latin1;