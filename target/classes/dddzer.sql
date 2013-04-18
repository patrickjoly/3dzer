SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `dddzer` ;
CREATE SCHEMA IF NOT EXISTS `dddzer` DEFAULT CHARACTER SET utf8 ;
USE `dddzer` ;

-- -----------------------------------------------------
-- Table `dddzer`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`account` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `is_enabled` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) ,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`role` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`account_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`account_role` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`account_role` (
  `account_id` INT(11) NOT NULL ,
  `role_id` INT(11) NOT NULL ,
  PRIMARY KEY (`account_id`, `role_id`) ,
  INDEX `fk_member_role_1_idx` (`account_id` ASC) ,
  INDEX `fk_member_role_2_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_member_role_1`
    FOREIGN KEY (`account_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_member_role_2`
    FOREIGN KEY (`role_id` )
    REFERENCES `dddzer`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`category` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `description_fr` VARCHAR(45) NULL DEFAULT NULL ,
  `description_en` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`part`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`part` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`part` (
  `id` INT(11) NOT NULL ,
  `account_id` INT(11) NOT NULL ,
  `name` VARCHAR(45) NULL DEFAULT NULL ,
  `model` VARCHAR(45) NULL DEFAULT NULL ,
  `serial` VARCHAR(45) NULL DEFAULT NULL ,
  `part_code` VARCHAR(45) NULL DEFAULT NULL ,
  `part_name` VARCHAR(45) NULL DEFAULT NULL ,
  `category_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_PART_ACCOUNT1_idx` (`account_id` ASC) ,
  INDEX `fk_PART_CATEGORY1_idx` (`category_id` ASC) ,
  CONSTRAINT `fk_PART_ACCOUNT1`
    FOREIGN KEY (`account_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PART_CATEGORY1`
    FOREIGN KEY (`category_id` )
    REFERENCES `dddzer`.`category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`product_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`product_type` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`product_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `dddzer`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`product` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `product_type_id` INT(11) NOT NULL ,
  `product_size` INT(11) UNSIGNED NULL DEFAULT NULL ,
  `product_file_name` VARCHAR(255) NULL DEFAULT NULL ,
  `product_content_type` VARCHAR(45) NULL DEFAULT NULL ,
  `product_binary` BLOB NULL DEFAULT NULL ,
  `description_` VARCHAR(255) NULL DEFAULT NULL ,
  `price` DECIMAL(10,0) NULL DEFAULT NULL ,
  `part_id` INT(11) NOT NULL ,
  `accout_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_product_product_type1_idx` (`product_type_id` ASC) ,
  INDEX `fk_product_part1_idx` (`part_id` ASC) ,
  INDEX `fk_PRODUCT_ACCOUNT1_idx` (`accout_id` ASC) ,
  CONSTRAINT `fk_product_product_type1`
    FOREIGN KEY (`product_type_id` )
    REFERENCES `dddzer`.`product_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_part1`
    FOREIGN KEY (`part_id` )
    REFERENCES `dddzer`.`part` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_ACCOUNT1`
    FOREIGN KEY (`accout_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`user` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NULL DEFAULT NULL ,
  `enbled` TINYINT(4) NULL DEFAULT NULL ,
  `first_name` VARCHAR(100) NULL DEFAULT NULL ,
  `last_name` VARCHAR(100) NULL DEFAULT NULL ,
  `street` VARCHAR(100) NULL DEFAULT NULL ,
  `city` VARCHAR(100) NULL DEFAULT NULL ,
  `country` VARCHAR(100) NULL DEFAULT NULL ,
  `zip` VARCHAR(100) NULL DEFAULT NULL ,
  `creation_date` TIMESTAMP NULL DEFAULT NULL ,
  `creation_author` VARCHAR(100) NULL DEFAULT NULL ,
  `modification_date` TIMESTAMP NULL DEFAULT NULL ,
  `modification_author` VARCHAR(100) NULL DEFAULT NULL ,
  `version` INT(11) NULL DEFAULT NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_USER_ACCOUNT2_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_USER_ACCOUNT2`
    FOREIGN KEY (`account_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dddzer`.`faq_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`faq_question` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`faq_question` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `anwser` VARCHAR(200) NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_FAQ_QUESTION_ACCOUNT1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_FAQ_QUESTION_ACCOUNT1`
    FOREIGN KEY (`account_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dddzer`.`faq_answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dddzer`.`faq_answer` ;

CREATE  TABLE IF NOT EXISTS `dddzer`.`faq_answer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `question` VARCHAR(200) NULL ,
  `faq_question_id` INT NOT NULL ,
  `account_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_FAQ_ANSWER_FAQ_QUESTION1_idx` (`faq_question_id` ASC) ,
  INDEX `fk_faq_answer_account1_idx` (`account_id` ASC) ,
  CONSTRAINT `fk_FAQ_ANSWER_FAQ_QUESTION1`
    FOREIGN KEY (`faq_question_id` )
    REFERENCES `dddzer`.`faq_question` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faq_answer_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `dddzer`.`account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `dddzer` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
