CREATE USER 'fixer'@'%' IDENTIFIED BY 'ratesdb';
GRANT ALL PRIVILEGES ON fixer.* TO 'fixer'@'%';


FLUSH PRIVILEGES;
create database fixer;
