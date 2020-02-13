-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema masajes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema masajes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `masajes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci ;
USE `masajes` ;

-- -----------------------------------------------------
-- Table `masajes`.`servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masajes`.`servicios` (
  `idservicios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL(6,3) NOT NULL,
  PRIMARY KEY (`idservicios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masajes`.`trabajadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masajes`.`trabajadores` (
  `idtrabajadores` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(90) NOT NULL,
  `dni` CHAR(9) NOT NULL,
  PRIMARY KEY (`idtrabajadores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masajes`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masajes`.`clientes` (
  `idclientes` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(90) NULL,
  `dni` CHAR(9) NULL,
  PRIMARY KEY (`idclientes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masajes`.`sesiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `masajes`.`sesiones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `clientes_idclientes` INT NOT NULL,
  `trabajadores_idtrabajadores` INT NOT NULL,
  `servicios_idservicios` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `resena` TEXT(1000) NULL,
  `calificacion` VARCHAR(15) NULL,
  INDEX `fk_actuaciones_clientes_idx` (`clientes_idclientes` ASC) VISIBLE,
  INDEX `fk_actuaciones_trabajadores1_idx` (`trabajadores_idtrabajadores` ASC) VISIBLE,
  INDEX `fk_actuaciones_servicios1_idx` (`servicios_idservicios` ASC) VISIBLE,
  UNIQUE INDEX `actuacion_UNIQUE` (`clientes_idclientes` ASC, `trabajadores_idtrabajadores` ASC, `servicios_idservicios` ASC, `fecha` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_actuaciones_clientes`
    FOREIGN KEY (`clientes_idclientes`)
    REFERENCES `masajes`.`clientes` (`idclientes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_actuaciones_trabajadores1`
    FOREIGN KEY (`trabajadores_idtrabajadores`)
    REFERENCES `masajes`.`trabajadores` (`idtrabajadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_actuaciones_servicios1`
    FOREIGN KEY (`servicios_idservicios`)
    REFERENCES `masajes`.`servicios` (`idservicios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
