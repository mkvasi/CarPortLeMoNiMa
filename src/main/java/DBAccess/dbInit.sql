
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
DROP DATABASE `fog`;
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`USERS` (
  `id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL AUTO_INCREMENT,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `zipcode` INT NOT NULL,
  `town` VARCHAR(45) NOT NULL,
  `phone number` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`EMPLOYEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`EMPLOYEE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `admin` TINYINT(2) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`CARPORT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`CARPORT` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `heigth` INT(11) NOT NULL,
  `width` INT(11) NOT NULL,
  `length` INT(11) NOT NULL,
  `roof` TINYINT(4) NOT NULL,
  `user_id` INT NULL,
  `employee_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`user_id` ASC),
  INDEX `id_idx1` (`employee_id` ASC),
FOREIGN KEY (user_id) REFERENCES `USERS`(id),
FOREIGN KEY (employee_id) REFERENCES `EMPLOYEE`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`MATERIALS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`MATERIALS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NOT NULL,
  `type` VARCHAR(40) NOT NULL,
  `buy_price` DOUBLE NOT NULL,
  `sell_price` DOUBLE NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `measure` VARCHAR(45) NOT NULL,
  `qty` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fog`.`CARPORT_MATERIALS`
-- -----------------------------------------------------



INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name, measure) values (1, 'Stolpe til carport', 'Træ', 80, 130, 'Stolpe', 'stk'); 
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name, measure) values (2, 'Beslag til stolpe', 'Beslag', 5, 12, 'Beslag', 'stk');
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name, measure) values (3, 'Skrue til beslag', 'Skruer', 0.5, 0.8, 'Skrue til beslag', 'stk'); 
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name,measure) values (4, 'Spær til at holde tagplader', 'Træ', 10, 25, 'Spær','meter'); 
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name,measure) values (5, 'Skruer til spær', 'Spær-skruer', 0.5, 0.8, 'Skruer til spær', 'stk'); 
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name,measure) values (6, 'Remme til carport', 'Remme', 600, 900, 'Remme', 'stk'); 
INSERT INTO MATERIALS (id, description, `type`, `buy_price`,`sell_price`,name,measure) values (7, 'Tagplade', 'Plastmo Ecolite blpåtonet', 0.5, 0.8, 'Tagplade', 'stk á 6 meter'); 



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
