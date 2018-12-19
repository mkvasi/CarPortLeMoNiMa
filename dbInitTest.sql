-- Schema fog
-- -----------------------------------------------------
DROP DATABASE `fogtest`;
CREATE SCHEMA IF NOT EXISTS `fogtest` DEFAULT CHARACTER SET utf8 ;
USE `fogtest` ;
  
-- -----------------------------------------------------
-- Table `fogtest`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`CUSTOMER` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `zipcode` INT(11) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `phonenumber` INT(11) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(255) NOT NULL DEFAULT 'customer',
  UNIQUE KEY (`email`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`EMPLOYEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`EMPLOYEE` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `admin` TINYINT(1) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(255) NOT NULL DEFAULT 'employee',
  UNIQUE KEY (`email`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`SHED`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`SHED` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `heigth` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  `length` DOUBLE NOT NULL,
  `shedcladding` INT(11) NOT NULL, 
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`CARPORT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`CARPORT` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `heigth` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  `length` DOUBLE NOT NULL,
  `roofslopecelsius` INT(11) NOT NULL,
  `roofcladding` INT(11) NOT NULL,
  `shed_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `shedid_index` (`shed_id` ASC),
  FOREIGN KEY (shed_id) REFERENCES `SHED`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`MATERIALS_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`MATERIALS_TYPE` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`MATERIALS_MEASURE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`MATERIALS_MEASURE` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `measure` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`MATERIALS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`MATERIALS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `heigth` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  `length` DOUBLE NOT NULL,
  `buyprice` DECIMAL(10,2) NOT NULL,
  `sellprice` DECIMAL(10,2) NOT NULL,
  `defaultused` TINYINT(1) NOT NULL,
  `type_id` INT(11) NOT NULL,
  `measure_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `typeid_index` (`type_id` ASC),
  INDEX `measureid_index` (`measure_id` ASC),
  FOREIGN KEY (type_id) REFERENCES `MATERIALS_TYPE`(id),
  FOREIGN KEY (measure_id) REFERENCES `MATERIALS_MEASURE`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`REQUEST` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `requestdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `offerdate` DATETIME DEFAULT NULL,
  `paymentdate` DATETIME DEFAULT NULL,
  `pricedefault` DECIMAL(10,2) NOT NULL,
  `priceemployee` DECIMAL(10,2) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `employee_id` INT(11) DEFAULT NULL,
  `carport_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customerid_index` (`customer_id` ASC),
  INDEX `employeeid_index` (`employee_id` ASC),
  INDEX `carportid_index` (`carport_id` ASC),
FOREIGN KEY (customer_id) REFERENCES `CUSTOMER`(id),
FOREIGN KEY (employee_id) REFERENCES `EMPLOYEE`(id),
FOREIGN KEY (carport_id) REFERENCES `CARPORT`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`BILLOFMATERIAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`BILLOFMATERIAL` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `qty` INT(11) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `helpdescription` VARCHAR(255) NOT NULL,
  `length` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`REQUEST_HAS_BILLOFMATERIAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`REQUEST_HAS_BILLOFMATERIAL` (
  `request_id` INT(11) NOT NULL,
  `billofmaterial_id` INT(11) NOT NULL,
  INDEX `requestid_index` (`request_id` ASC),
  INDEX `billofmaterialid_index` (`billofmaterial_id` ASC),
  FOREIGN KEY (request_id) REFERENCES `REQUEST`(id),
  FOREIGN KEY (billofmaterial_id) REFERENCES `BILLOFMATERIAL`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- Table `fogtest`.`CARPORT_HAS_MATERIALS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogtest`.`CARPORT_HAS_MATERIALS` (
  `carport_id` INT(11) NOT NULL,
  `materials_id` INT(11) NOT NULL,
  INDEX `carportid_index` (`carport_id` ASC),
  INDEX `matrialsid_index` (`materials_id` ASC),
  FOREIGN KEY (carport_id) REFERENCES `CARPORT`(id),
  FOREIGN KEY (materials_id) REFERENCES `MATERIALS`(id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
  
-- -----------------------------------------------------
-- INSERT INTO TABLE MATERIALS_MEASURE DEFAULT VALUES
-- -----------------------------------------------------
INSERT INTO MATERIALS_MEASURE (`measure`) VALUES
('stk.'),
('pakke'),
('rulle'),
('sæt');
  
  
-- -----------------------------------------------------
-- INSERT INTO TABLE MATERIALS_TYPE DEFAULT VALUES
-- -----------------------------------------------------
INSERT INTO MATERIALS_TYPE (`type`) VALUES
('træ (brædder ubh.)'),
('tag (plader)'),
('tag (sten)'),
('skruer'),
('beslag'),
('træ (brædder trykimp.)'),
('træ (stolpe trykimp.)'),
('træ (lægte ubh.)'),
('træ (reglar ubh.)'),
('træ (spærtræ ubh.)'),
('træ (byg-selv-spær)'),
('beslag (højre)'),
('beslag (venstre)'),
('brædderbolte'),
('tag (rygsten)'); 
  
-- -----------------------------------------------------
-- INSERT INTO TABLE MATERIALS DEFAULT VALUES
-- -----------------------------------------------------
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X125 MM VTA TRYKIMPR.',19,125,3000,60,80.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X125 MM VTA TRYKIMPR.',19,125,3600,72,97.02,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X125 MM VTA TRYKIMPR.',19,125,4200,84,113.19,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X125 MM VTA TRYKIMPR.',19,125,4800,96,129.36,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,3000,105,128.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,3600,126,154.62,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,4200,147,180.39,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,4800,168,206.16,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,5400,189,231.93,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X175 MM VTA TRYKIMPR.',25,175,6000,210,257.7,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,3000,120,149.85,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,3600,144,179.82,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,4200,168,209.79,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,4800,192,239.76,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,5400,216,269.73,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X200 MM VTA TRYKIMPR.',25,200,6000,240,299.7,true,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,1800,18,35.91,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,2100,21,41.95,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,2400,24,47.88,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,2700,27,53.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,3000,30,59.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,3300,33,65.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,3600,36,71.82,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,3900,39,77.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,4200,42,83.79,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,4500,45,89.75,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,4800,48,95.76,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,5100,51,101.75,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X75 MM VTA TRYKIMPR.',19,75,5400,54,107.73,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,1800,45,57.51,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,2100,52.5,67.95,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,2400,60,76.68,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,2700,67.5,86.25,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,3000,75,95.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,3300,82.5,105.45,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,3600,90,115.02,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,3900,97.5,124.60,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,4200,105,134.19,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,4500,112.5,143.75,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,4800,120,153.36,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,5100,127.5,162.95,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X150 MM VTA TRYKIMPR.',19,150,5400,135,172.53,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,3000,45,74.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,3600,54,89.82,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,4200,63,104.79,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,4800,72,119.76,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,5400,81,134.73,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X100 MM VTA TRYKIMPR.',25,100,6000,90,149.7,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,3000,75,92.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,3600,90,111.42,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,4200,105,129.99,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,4800,120,148.56,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,5400,135,167.13,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X125 MM VTA TRYKIMPR.',25,100,6000,150,185.7,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,1800,9,17.91,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,2100,10.5,208.95,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,2400,12,23.88,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,2700,13.5,268.65,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,3000,15,29.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,3600,18,35.82,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('19X100 MM FYR SEKSTA TRYKIMPR.',19,100,4200,21,41.79,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('97X97 MM FYR STOLPE IMPR.',97,97,2400,200,261.6,true,7,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('97X97 MM FYR STOLPE IMPR.',97,97,2700,200,294,true,7,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('97X97 MM FYR STOLPE IMPR.',97,97,3000,200,327,true,7,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('97X97 MM FYR STOLPE IMPR.',97,97,3600,200,392.4,true,7,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,2400,15,23.88,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,2700,15,26.86,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,3000,15,26.85,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,3300,15,29.54,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,3600,15,32.23,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,3900,15,34.9,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,4200,15,37.59,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,4500,15,40.28,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,4800,15,42.96,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,5100,15,45.65,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('38X73 MM SKILLERUMSLÆGTE',38,73,5400,15,43.88,true,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,1800,100,125.91,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,2100,100,146,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,2400,100,167,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,2700,100,188,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,3000,100,209,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,3300,100,230,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,3600,100,251,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,3900,100,272,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,4200,100,293,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,4500,100,314,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,4800,100,335,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,5100,100,356,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X150 MM FYR',50,150,5400,100,377,true,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,1800,75,84.51,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,2100,75,98,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,2400,75,112,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,2700,75,126,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,3000,75,140,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,3300,75,154,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,3600,75,169,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,3900,75,183,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,4200,75,197,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,4500,75,211,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,4800,75,225,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,5100,75,239,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X100 MM FYR',50,100,5400,75,253,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,1800,90,104,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,2100,90,121,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,2400,90,139,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,2700,90,156,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,3000,90,173,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,3300,90,191,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,3600,90,208,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,3900,90,226,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,4200,90,243,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,4500,90,260,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,4800,90,278,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,5100,90,295,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('50X125 MM FYR',50,125,5400,90,312,false,9,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,3000,100,131,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,3600,100,158,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,4200,100,184,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,4800,100,210,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,5400,100,237,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,6000,100,299,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,6600,100,329,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X200 MM SPÆRTRÆ C18 HØVLET',45,195,7200,100,359,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,3000,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,3600,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,4200,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,4800,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,5400,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,6000,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,6600,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('47X150 MM SPÆRTRÆ C18 HØVLET',45,145,7200,80,104,false,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,2400,60,80,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,3000,60,99,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,3600,60,119,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,4200,60,149,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,4800,60,179,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE BLAETONET PLASTMO ECOLITE',1,1090,6000,60,289,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE PLASTMO ECOLITE',1,1090,2400,200,219,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE PLASTMO ECOLITE',1,1090,3000,200,279,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE PLASTMO ECOLITE',1,1090,3600,200,339,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE PLASTMO ECOLITE',1,1090,4200,200,389,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('TRAPEZPLADE PLASTMO ECOLITE',1,1090,4800,200,420,false,2,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,3000,60,80.85,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,3600,60,110,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,4200,60,130,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,4800,60,150,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,5400,60,170,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('25X50 MM VTA TRYKIMPR.',25,50,6000,60,190,false,6,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES)',1,1,1,50,75,true,10,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('PASLODE BESLAGSKRUE 5X40MM TWINCUT DOB.L. GEVIND 250 STK.',5,5,40,60,85,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 5,0 X 100 MM. 100 STK.',5,5,100,50,65,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 4,5 X 70 MM. 200 STK.',4.5,4.5,70,80,90,true,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 4,5 X 50 MM. 350 STK.',4.5,4.5,50,90,110,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('PLASTMO SKRUER(BUNDSKRUNING) T/TRAPEZ ÆSKE A 200 STK',1,1,1,50,399,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 4,5 X 60 MM. 200 STK.',4.5,4.5,60,60,80,true,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('PASLODE BESLAGSKRUE 4X50MM TWINCUT DOB.L. GEVIND 250 STK.',4,4,50,70,90,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 4,5 X 70 MM. 400 STK.',4.5,4.5,70,80,100,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('SKRUER 4,5 X 50 MM. 300 STK.',4.5,4.5,50,90,110,false,4,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('PASLODE HULBÅND TYPE 20-1 20X1,0MM - 10M VFZ RULLE Á 10 MTR.',1,20,10000,180,209,false,5,3);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C DOBBELT -S SORT 300 STK.',1,1,1,35,50,false,3,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C RYGSTEN SORT 21 STK.',1,1,1,170,200,false,15,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C TOPLAEGTE HOLDER 8 STK.',1,1,1,100,150,false,8,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C RYGSTENSBESLAG 21 STK.',1,1,1,170,200,false,5,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C TAGSTENS BINDERE & NAKKEKROGE',1,1,1,150,175,false,5,2);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('UNIVERSAL 190 MM HØJRE',1,1,190,170,200,true,12,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('UNIVERSAL 190 MM VENSTRE',1,1,190,180,200,true,13,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('BRÆDDEBOLT',1,1,120,20,50,true,14,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('FIRKANTSKIVER',11,11,40,10,25,false,5,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('STALDDØRSGREB',75,75,50,500,590,false,5,4);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('T HÆNGSEL 390 MM',1,1,1,700,800,false,5,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('VINKELBESLAG 35',1,1,1,200,250,false,5,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C DOBBELT -S SORT HOEJGLANS 300 STK.',1,1,1,50,70,false,3,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C DOBBELT -S BRUN 300 STK.',1,1,1,25,45,false,3,1);
INSERT INTO MATERIALS (`description`, `heigth`, `width`, `length`, `buyprice`, `sellprice`, `defaultused`, `type_id`, `measure_id`) VALUES ('B & C DOBBELT -S BRUN HOEJGLANS 300 STK.',1,1,1,50,70,false,3,1);


-- -----------------------------------------------------
-- INSERT INTO TABLE CUSTOMER DEFAULT VALUES
-- -----------------------------------------------------
INSERT INTO customer (firstname, lastname, email, zipcode, city, phonenumber, `password`) VALUES ('testFor', 'testLast', 'customer@customer.dk', 2500, 'testCity', 12345678, '1234');


-- -----------------------------------------------------
-- INSERT INTO TABLE EMPLOYEE DEFAULT VALUES
-- -----------------------------------------------------
INSERT INTO employee (firstname, lastname, email, admin, `password`) VALUES ('employeeFor', 'employeeLast', 'employee@employee.dk', false, '1234');