CREATE SCHEMA IF NOT EXISTS `WareHouse` DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS `WareHouse`.`Customers` (
  `CustomerId` INT NOT NULL AUTO_INCREMENT,
  `CustomerName` VARCHAR(45) NULL,
  `ContactPerson` VARCHAR(45) NULL,
  `Adress` VARCHAR(45) NULL,
  `City` VARCHAR(45) NULL,
  `PostCode` VARCHAR(10) NULL,
  `Country` VARCHAR(45) NULL,
  PRIMARY KEY (`CustomerId`));
CREATE TABLE IF NOT EXISTS `WareHouse`.`Employees` (
  `EmployeeId` INT NOT NULL AUTO_INCREMENT,
  `LastName` VARCHAR(45) NULL,
  `FirstName` VARCHAR(45) NULL,
  `BirthDate` DATE NULL,
  PRIMARY KEY (`EmployeeId`));
CREATE TABLE IF NOT EXISTS `WareHouse`.`Suppliers` (
  `SupplierId` INT NOT NULL AUTO_INCREMENT,
  `SupplierName` VARCHAR(45) NULL,
  `ContactPerson` VARCHAR(45) NULL,
  `Adress` VARCHAR(45) NULL,
  `City` VARCHAR(45) NULL,
  `PostCode` VARCHAR(10) NULL,
  `Country` VARCHAR(45) NULL,
  `Phone` VARCHAR(10) NULL,
  PRIMARY KEY (`SupplierId`));
CREATE TABLE IF NOT EXISTS `WareHouse`.`Shippers` (
  `ShipperId` INT NOT NULL AUTO_INCREMENT,
  `ShipperName` VARCHAR(45) NULL,
  `Phone` VARCHAR(10) NULL,
  PRIMARY KEY (`ShipperId`));
CREATE TABLE IF NOT EXISTS `WareHouse`.`Products` (
  `ProductId` INT NOT NULL AUTO_INCREMENT,
  `ProductName` VARCHAR(45) NULL,
  `SupplierId` INT NULL,
  `ProductCategory` VARCHAR(45) NULL,
  `PricePerUnit` DOUBLE NULL,
  PRIMARY KEY (`ProductId`),
  INDEX `SupplierId_idx` (`SupplierId` ASC),
  CONSTRAINT `SupplierId`
    FOREIGN KEY (`SupplierId`)
    REFERENCES `WareHouse`.`Suppliers` (`SupplierId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE IF NOT EXISTS `WareHouse`.`Orders` (
  `OrderId` INT NOT NULL AUTO_INCREMENT,
  `OrderDate` DATE NULL,
  `CustomerId` INT NULL,
  `EmployeeId` INT NULL,
  `ShipperId` INT NULL,
  PRIMARY KEY (`OrderId`),
  INDEX `CustomerId_idx` (`CustomerId` ASC),
  INDEX `EmployeeId_idx` (`EmployeeId` ASC),
  INDEX `ShipperId_idx` (`ShipperId` ASC),
  CONSTRAINT `CustomerId`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `WareHouse`.`Customers` (`CustomerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EmployeeId`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `WareHouse`.`Employees` (`EmployeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ShipperId`
    FOREIGN KEY (`ShipperId`)
    REFERENCES `WareHouse`.`Shippers` (`ShipperId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE IF NOT EXISTS `WareHouse`.`OrderDetails` (
  `OrderDetailsId` INT NOT NULL AUTO_INCREMENT,
  `OrderId` INT NULL,
  `ProductId` INT NULL,
  `Quantity` INT NULL,
  PRIMARY KEY (`OrderDetailsId`),
  INDEX `OrderId_idx` (`OrderId` ASC),
  INDEX `ProductId_idx` (`ProductId` ASC),
  CONSTRAINT `OrderId`
    FOREIGN KEY (`OrderId`)
    REFERENCES `WareHouse`.`Orders` (`OrderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ProductId`
    FOREIGN KEY (`ProductId`)
    REFERENCES `WareHouse`.`Products` (`ProductId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  