-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AdoptMe
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AdoptMe
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AdoptMe` DEFAULT CHARACTER SET utf8 ;
USE `AdoptMe` ;

-- -----------------------------------------------------
-- Table `AdoptMe`.`Shelters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AdoptMe`.`Shelters` ;

CREATE TABLE IF NOT EXISTS `AdoptMe`.`Shelters` (
  `shelterId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `webSite` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profileImg` VARCHAR(45) NULL,
  PRIMARY KEY (`shelterId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AdoptMe`.`Pets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AdoptMe`.`Pets` ;

CREATE TABLE IF NOT EXISTS `AdoptMe`.`Pets` (
  `petId` INT NOT NULL AUTO_INCREMENT,
  `shelter` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `imgSrc` VARCHAR(45) NULL DEFAULT NULL,
  `age` VARCHAR(45) NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL DEFAULT NULL,
  `type` INT NOT NULL COMMENT '0 => DOG,   1 => CAT',
  PRIMARY KEY (`petId`, `shelter`),
  INDEX `ShelterPet_fkey_idx` (`shelter` ASC) VISIBLE,
  CONSTRAINT `ShelterPet_fkey`
    FOREIGN KEY (`shelter`)
    REFERENCES `AdoptMe`.`Shelters` (`shelterId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AdoptMe`.`Requests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AdoptMe`.`Requests` ;

CREATE TABLE IF NOT EXISTS `AdoptMe`.`Requests` (
  `requestId` INT NOT NULL,
  `shelterId` INT NOT NULL,
  `petId` INT NOT NULL,
  `userId` INT NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`requestId`, `shelterId`),
  INDEX `pet_fk_idx` (`shelterId` ASC, `petId` ASC) VISIBLE,
  CONSTRAINT `pet_fk`
    FOREIGN KEY (`shelterId` , `petId`)
    REFERENCES `AdoptMe`.`Pets` (`shelter` , `petId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AdoptMe`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AdoptMe`.`Users` ;

CREATE TABLE IF NOT EXISTS `AdoptMe`.`Users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profileImg` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS user1;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'user1' IDENTIFIED BY 'user1';

GRANT ALL ON `AdoptMe`.* TO 'user1';
SET SQL_MODE = '';
DROP USER IF EXISTS shelter1;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'shelter1' IDENTIFIED BY 'shelter1';

GRANT ALL ON `AdoptMe`.* TO 'shelter1';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `AdoptMe`.`Shelters`
-- -----------------------------------------------------
START TRANSACTION;
USE `AdoptMe`;
INSERT INTO `AdoptMe`.`Shelters` (`shelterId`, `name`, `phoneNumber`, `address`, `city`, `webSite`, `email`, `password`, `profileImg`) VALUES (DEFAULT, 'Pensieri Bestiali', '3356278995', 'via Roma, 2', 'Roma', 'sito1', 'pensieri_bestiali@gmail.com', '123', NULL);
INSERT INTO `AdoptMe`.`Shelters` (`shelterId`, `name`, `phoneNumber`, `address`, `city`, `webSite`, `email`, `password`, `profileImg`) VALUES (DEFAULT, 'Amici Di Onda', '3399776534', 'via Cavour, 16', 'Roma', 'sito2', 'amici_di_onda@gmail.com', '456', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `AdoptMe`.`Pets`
-- -----------------------------------------------------
START TRANSACTION;
USE `AdoptMe`;
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (1, 1, 'Lilly', 'image/gatto1.png', 'Adult', 'Female', 1);
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (2, 1, 'Aron', 'image/cane1.png', 'Puppy', 'Male', 0);
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (3, 1, 'Kira', 'image/cane2.png', 'Young', 'Female', 0);
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (4, 1, 'Noa', 'image/gatto2.png', 'Adult', 'Male', 1);
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (5, 1, 'Ketty', 'image/gatto3.png', 'Puppy', 'Female', 1);
INSERT INTO `AdoptMe`.`Pets` (`petId`, `shelter`, `name`, `imgSrc`, `age`, `gender`, `type`) VALUES (6, 2, 'Amy', 'image/cane3.png', 'Adult ', 'Female', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `AdoptMe`.`Requests`
-- -----------------------------------------------------
START TRANSACTION;
USE `AdoptMe`;
INSERT INTO `AdoptMe`.`Requests` (`requestId`, `shelterId`, `petId`, `userId`, `date`, `time`) VALUES (1, 1, 1, 1, '2022-07-27', '16:00:00');
INSERT INTO `AdoptMe`.`Requests` (`requestId`, `shelterId`, `petId`, `userId`, `date`, `time`) VALUES (2, 1, 3, 2, '2022-06-06', '13:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AdoptMe`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `AdoptMe`;
INSERT INTO `AdoptMe`.`Users` (`userId`, `name`, `surname`, `email`, `password`, `profileImg`) VALUES (DEFAULT, 'Francesca', 'Pavone', 'francesca@gmail.com', '123', NULL);
INSERT INTO `AdoptMe`.`Users` (`userId`, `name`, `surname`, `email`, `password`, `profileImg`) VALUES (DEFAULT, 'Federica', 'Cantelmi', 'federica@gmail.com', '456', NULL);

COMMIT;

