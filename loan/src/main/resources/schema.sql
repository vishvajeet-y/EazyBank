CREATE TABLE IF NOT EXISTS `loans`(
`loan_id` int NOT NULL AUTO_INCREMENT,
`mobilenumber` varchar(15) NOT NULL,
`loan_number` varchar(100) NOT NULL,
`loan_type` varchar(100) ,
 `total_loan` int NOT NULL,
 `ammount_paid` int NOT NULL,
 `Outstading_ammount` int NOT NULL,
 `created_at` date NOT NULL,
 `created_by`varchar(20) NOT NULL,
 `updated_at` date NOT NULL,
 `updated_by` varchar(20) NOT NULL,
  PRIMARY KEY (`loan_id`)
);