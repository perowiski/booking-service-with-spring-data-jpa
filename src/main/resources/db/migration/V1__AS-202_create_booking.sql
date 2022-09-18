CREATE TABLE IF NOT EXISTS `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reference` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `column` bigint(20) NOT NULL,
  `row` bigint(20) NOT NULL,
  `time_created` datetime NOT NULL DEFAULT now(),
  `time_updated` datetime ON UPDATE now(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_reference_IDX` (`reference`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;