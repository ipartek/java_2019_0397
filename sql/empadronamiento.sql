-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema empadronamiento
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema empadronamiento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empadronamiento` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci ;
USE `empadronamiento` ;

-- -----------------------------------------------------
-- Table `empadronamiento`.`municipios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empadronamiento`.`municipios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` CHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empadronamiento`.`viviendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empadronamiento`.`viviendas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` CHAR(10) NOT NULL,
  `calle` VARCHAR(100) NOT NULL,
  `numero` VARCHAR(5) NULL,
  `municipios_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_viviendas_municipios1_idx` (`municipios_id` ASC) VISIBLE,
  CONSTRAINT `fk_viviendas_municipios1`
    FOREIGN KEY (`municipios_id`)
    REFERENCES `empadronamiento`.`municipios` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empadronamiento`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empadronamiento`.`personas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(90) NOT NULL,
  `dni` CHAR(9) NOT NULL,
  `dni_discriminante` CHAR(1) NULL,
  `habitar_vivienda_id` INT NOT NULL,
  `empadronada_vivienda_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_personas_viviendas_idx` (`habitar_vivienda_id` ASC) VISIBLE,
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC, `dni_discriminante` ASC) VISIBLE,
  INDEX `fk_personas_viviendas1_idx` (`empadronada_vivienda_id` ASC) VISIBLE,
  CONSTRAINT `fk_personas_viviendas`
    FOREIGN KEY (`habitar_vivienda_id`)
    REFERENCES `empadronamiento`.`viviendas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_viviendas1`
    FOREIGN KEY (`empadronada_vivienda_id`)
    REFERENCES `empadronamiento`.`viviendas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `empadronamiento`.`personas_propiedad_viviendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `empadronamiento`.`personas_propiedad_viviendas` (
  `personas_id` INT NOT NULL,
  `viviendas_id` INT NOT NULL,
  PRIMARY KEY (`personas_id`, `viviendas_id`),
  INDEX `fk_personas_has_viviendas_viviendas1_idx` (`viviendas_id` ASC) VISIBLE,
  INDEX `fk_personas_has_viviendas_personas1_idx` (`personas_id` ASC) VISIBLE,
  CONSTRAINT `fk_personas_has_viviendas_personas1`
    FOREIGN KEY (`personas_id`)
    REFERENCES `empadronamiento`.`personas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_has_viviendas_viviendas1`
    FOREIGN KEY (`viviendas_id`)
    REFERENCES `empadronamiento`.`viviendas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
