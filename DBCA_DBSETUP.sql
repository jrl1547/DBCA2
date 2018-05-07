-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema capstone
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema capstone
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS cap;
CREATE SCHEMA cap DEFAULT CHARACTER SET utf8 ;
USE cap ;

-- -----------------------------------------------------
-- Table `capstone`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `roleid` INT NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `department` VARCHAR(45) NULL,
  PRIMARY KEY (`username`, `roleid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`capstone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`capstone` (
  `capstoneid` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `typeid` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `abstract` MEDIUMTEXT NULL,
  `plagerismscore` VARCHAR(45) NULL,
  `grade` VARCHAR(45) NULL,
  `defensedate` VARCHAR(10) NULL,
  PRIMARY KEY (`capstoneid`, `username`, `typeid`),
  INDEX `fk_capstone_users_idx` (`username` ASC),
  CONSTRAINT `fk_capstone_users`
    FOREIGN KEY (`username`)
    REFERENCES `cap`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`committee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`committee` (
  `capstoneid` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `has_accepted` TINYINT NULL,
  `has_declined` TINYINT NULL,
  `positionid` INT NOT NULL,
  `tracking` TINYINT NULL,
  PRIMARY KEY (`capstoneid`, `username`, `positionid`),
  CONSTRAINT `fk_committee_capstone`
    FOREIGN KEY (`capstoneid`)
    REFERENCES `cap`.`capstone` (`capstoneid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_committee_username`
        FOREIGN KEY (`username`)
        REFERENCES `cap`.`users` (`username`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`studentdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`studentdetails` (
  `username` VARCHAR(45) NOT NULL,
  `mastersstart` VARCHAR(45) NULL,
  `capstonestart` VARCHAR(45) NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_studentdetails_users`
    FOREIGN KEY (`username`)
    REFERENCES `cap`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`ritcalendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`ritcalendar` (
  `term` INT NOT NULL,
  `startdate` VARCHAR(45) NULL,
  `adddropdeadline` VARCHAR(45) NULL,
  `gradedeadline` VARCHAR(45) NULL,
  `enddate` VARCHAR(45) NULL,
  PRIMARY KEY (`term`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `capstone`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`status` (
  `statusid` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `stepcode` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`statusid`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `capstone`.`statushistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`statushistory` (
  `statusid` INT NOT NULL,
  `capstoneid` INT NOT NULL,
  `date` DATETIME(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `capstoneid_UNIQUE` (`capstoneid` ASC),
  CONSTRAINT `fk_statushistory_capstone`
    FOREIGN KEY (`capstoneid`)
    REFERENCES `cap`.`capstone` (`capstoneid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `capstone`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`roles` (
  `roleid` INT NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`types` (
  `typeid` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`typeid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `capstone`.`positions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cap`.`positions` (
  `positionid` INT NOT NULL,
  `position` VARCHAR(45) NULL,
  PRIMARY KEY (`positionid`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `capstone`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststudent', sha1('password'), 1, 'Test Student', 'test1@test.com', '585-555-1234', 'IST');
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststaff', sha1('password'), 2, 'Test Staff', NULL, '585-555-1234', NULL);
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('testfaculty', sha1('password'), 3, 'Test Faculty', NULL, '585-555-1234', NULL);
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('testfaculty2', sha1('password'), 3, 'Test Faculty', NULL, '585-555-1234', NULL);
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('testsuper', sha1('password'), 4, 'Test Super', NULL, '585-555-1234', NULL);
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststudent2', sha1('password'), 1, 'Test Student2', 'test2@test.com', NULL, 'IST');
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststudent3', sha1('password'), 1, 'Test Student3', 'test3@test.com', NULL, 'IST');
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststudent4', sha1('password'), 1, 'Test Student4', 'test4@test.com', NULL, 'IST');
INSERT INTO `cap`.`users` (`username`, `password`, `roleid`, `fullname`, `email`, `phone`, `department`) VALUES ('teststudent5', sha1('password'), 1, 'Test Student5', 'test5@test.com', NULL, 'IST');

COMMIT;

-- -----------------------------------------------------
-- Data for table `capstone`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`studentdetails` (`username`) VALUES ('teststudent');
INSERT INTO `cap`.`studentdetails` (`username`) VALUES ('teststudent2');
INSERT INTO `cap`.`studentdetails` (`username`) VALUES ('teststudent3');
INSERT INTO `cap`.`studentdetails` (`username`) VALUES ('teststudent4');
INSERT INTO `cap`.`studentdetails` (`username`) VALUES ('teststudent5');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`capstone`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`capstone` (`username`, `capstoneid`, `typeid`, `title`, `abstract`, `plagerismscore`, `grade`) VALUES ('teststudent', 1, 1, '\"A test capstone\"', '\"This is a test.\"', NULL, NULL);
INSERT INTO `cap`.`capstone` (`username`, `capstoneid`, `typeid`, `title`, `abstract`, `plagerismscore`, `grade`) VALUES ('teststudent2', 2, 1, '\"A test capstone2\"', '\"This is a test2.\"', NULL, NULL);
INSERT INTO `cap`.`capstone` (`username`, `capstoneid`, `typeid`, `title`, `abstract`, `plagerismscore`, `grade`) VALUES ('teststudent3', 3, 1, '\"A test capstone3\"', '\"This is a test3.\"', NULL, NULL);
INSERT INTO `cap`.`capstone` (`username`, `capstoneid`, `typeid`, `title`, `abstract`, `plagerismscore`, `grade`) VALUES ('teststudent4', 4, 1, '\"A test capstone4\"', '\"This is a test4.\"', NULL, NULL);
INSERT INTO `cap`.`capstone` (`username`, `capstoneid`, `typeid`, `title`, `abstract`, `plagerismscore`, `grade`) VALUES ('teststudent5', 5, 1, '\"A test capstone5\"', '\"This is a test5.\"', '85', 'F');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`committee`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (1, 'teststudent', 0, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (2, 'teststudent2', 0, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (3, 'teststudent3', 0, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (4, 'teststudent4', 0, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (5, 'teststudent5', 0, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (1, 'teststaff', 1, 0, 1, 0);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (1, 'testfaculty', 0, 1, 4, 1);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (5, 'testfaculty', 1, 0, 4, 1);
INSERT INTO `cap`.`committee` (`capstoneid`, `username`, `has_accepted`, `has_declined`, `positionid`, `tracking`) VALUES (2, 'testfaculty', 0, 0, 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`status`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (100, 'Pre proposal', 'status', '1. Pre-proprosal (optional but very useful)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (200, 'Committee formed', 'student', '2. Forming the committee (two for project and three for thesis)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (250, '', 'instruction', '2.1 Download the proposal approval form from http://ist.rit.edu/');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (300, 'Proposal development', 'status', '3. Continue proposal development ');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (400, 'Proposal approved', '', '4. Proposal approved');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (500, 'Get prop. approval form', '', '4.1 Download the proposal approval form from the IST website (under forms)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (600, 'Proposal signed ', '', '4.2 Collect signatures from committee members (email approval is fine)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (700, 'Proposal & form to IST', '', '4.3 Submit the approved proposal form along with the electronic version of the proposal to the department office (Tracy)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (800, 'Plagiarism checked', '', '4.4 The IST department office performs plagiarism checking and sends the results and proposal to the Graduate Director for final approval');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (900, 'Grad director approved', '', '4.5 After the approval from the Graduate Director');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1000, 'Working on capstone', '', '5. Continue the capstone work based on what is proposed in the proposal. ');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1100, 'Work & report finished', '', '6. Finish the work and complete the final report.');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1200, 'Work & report approved', '', '7. Get approval from the committee to schedule the final defense.');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1300, 'Defense completed', '', '7.1 Conduct the defense');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1400, 'Report revised, post defense', '', '8. Revise the final report based on committee feedback. (if committee requires revisions)');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1500, 'Final committee approval', '', '9. After getting the final approval from the committee');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1600, 'Grade reported', '', '10. Committee chair report the capstone grade to the IST department office.');
INSERT INTO `cap`.`status` (`statusid`, `name`, `stepcode`, `description`) VALUES (1700, 'Capstone finished', '', '11. All work for capstone is completed by the students, staff and faculty.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`statushistory`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 100, '2017-12-1 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 200, '2017-12-2 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 300, '2017-12-5 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 400, '2017-12-6 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 500, '2017-12-8 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 600, '2017-12-15 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 700, '2017-12-16 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 800, '2017-12-21 12:00:00');
INSERT INTO `cap`.`statushistory` (`capstoneid`, `statusid`, `date`) VALUES (5, 1600, '2017-12-22 12:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`roles` (`roleid`, `role`) VALUES (1, 'student');
INSERT INTO `cap`.`roles` (`roleid`, `role`) VALUES (2, 'staff');
INSERT INTO `cap`.`roles` (`roleid`, `role`) VALUES (3, 'faculty');
INSERT INTO `cap`.`roles` (`roleid`, `role`) VALUES (4, 'super');
INSERT INTO `cap`.`roles` (`roleid`, `role`) VALUES (5, 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`types`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`types` (`typeid`, `type`) VALUES (1, 'project');
INSERT INTO `cap`.`types` (`typeid`, `type`) VALUES (2, 'thesis');

COMMIT;


-- -----------------------------------------------------
-- Data for table `capstone`.`positions`
-- -----------------------------------------------------
START TRANSACTION;
USE `cap`;
INSERT INTO `cap`.`positions` (`positionid`, `position`) VALUES (1, 'student');
INSERT INTO `cap`.`positions` (`positionid`, `position`) VALUES (2, 'chair');
INSERT INTO `cap`.`positions` (`positionid`, `position`) VALUES (3, 'reader');
INSERT INTO `cap`.`positions` (`positionid`, `position`) VALUES (4, 'tracking');

COMMIT;

