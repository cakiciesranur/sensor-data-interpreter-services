CREATE DATABASE IF NOT EXISTS device_db;
use device_db;
CREATE TABLE IF NOT EXISTS `OPTIMAL_TEMPERATURE`
(`ID` INT NOT NULL ,
`DEVICE_TYPE` VARCHAR(50) NOT NULL ,
`TEMPERATURE` INT NOT NULL );
INSERT INTO `OPTIMAL_TEMPERATURE`(`ID`, `DEVICE_TYPE`, `TEMPERATURE`) VALUES ('01','DEVICE1','75');
INSERT INTO `OPTIMAL_TEMPERATURE`(`ID`, `DEVICE_TYPE`, `TEMPERATURE`) VALUES ('02','DEVICE2','20');
INSERT INTO `OPTIMAL_TEMPERATURE`(`ID`, `DEVICE_TYPE`, `TEMPERATURE`) VALUES ('03','DEVICE3','45');
INSERT INTO `OPTIMAL_TEMPERATURE`(`ID`, `DEVICE_TYPE`, `TEMPERATURE`) VALUES ('04','DEVICE4','30');
INSERT INTO `OPTIMAL_TEMPERATURE`(`ID`, `DEVICE_TYPE`, `TEMPERATURE`) VALUES ('05','DEVICE5','50');
