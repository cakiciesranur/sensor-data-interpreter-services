CREATE DATABASE IF NOT EXISTS device_db;
use device_db;
CREATE TABLE IF NOT EXISTS `optimal_temperature`
(`id` INT NOT NULL ,
`device_type` VARCHAR(50) NOT NULL ,
`temperature` INT NOT NULL );
INSERT INTO `optimal_temperature`(`id`, `device_type`, `temperature`) VALUES ('01','DEVICE1','75');
INSERT INTO `optimal_temperature`(`id`, `device_type`, `temperature`) VALUES ('02','DEVICE2','20');
INSERT INTO `optimal_temperature`(`id`, `device_type`, `temperature`) VALUES ('03','DEVICE3','45');
INSERT INTO `optimal_temperature`(`id`, `device_type`, `temperature`) VALUES ('04','DEVICE4','30');
INSERT INTO `optimal_temperature`(`id`, `device_type`, `temperature`) VALUES ('05','DEVICE5','50');
