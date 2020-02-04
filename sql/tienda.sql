-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tienda` DEFAULT CHARACTER SET utf8 ;
USE `tienda` ;

-- -----------------------------------------------------
-- Table `tienda`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`categorias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`productos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DECIMAL NOT NULL,
  `categorias_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_productos_categorias_idx` (`categorias_id` ASC) VISIBLE,
  CONSTRAINT `fk_productos_categorias`
    FOREIGN KEY (`categorias_id`)
    REFERENCES `tienda`.`categorias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`empleados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `jefe_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_empleados_empleados1_idx` (`jefe_id` ASC) VISIBLE,
  CONSTRAINT `fk_empleados_empleados1`
    FOREIGN KEY (`jefe_id`)
    REFERENCES `tienda`.`empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`facturas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NOT NULL,
  `numero_factura` CHAR(8) NOT NULL,
  `clientes_id` INT NOT NULL,
  `empleados_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facturas_clientes1_idx` (`clientes_id` ASC) VISIBLE,
  INDEX `fk_facturas_empleados1_idx` (`empleados_id` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_clientes1`
    FOREIGN KEY (`clientes_id`)
    REFERENCES `tienda`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facturas_empleados1`
    FOREIGN KEY (`empleados_id`)
    REFERENCES `tienda`.`empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`facturas_detalles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`facturas_detalles` (
  `facturas_id` INT NOT NULL,
  `productos_id` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`facturas_id`, `productos_id`),
  INDEX `fk_facturas_has_productos_productos1_idx` (`productos_id` ASC) VISIBLE,
  INDEX `fk_facturas_has_productos_facturas1_idx` (`facturas_id` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_has_productos_facturas1`
    FOREIGN KEY (`facturas_id`)
    REFERENCES `tienda`.`facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facturas_has_productos_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `tienda`.`productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tienda`.`secciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`secciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `jefe_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_secciones_empleados1_idx` (`jefe_id` ASC) VISIBLE,
  CONSTRAINT `fk_secciones_empleados1`
    FOREIGN KEY (`jefe_id`)
    REFERENCES `tienda`.`empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
