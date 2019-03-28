# MYSQL with docker

Mysql adatbázis szerver indítása docker konténerből.
```bash
docker pull mysql
#konténer létrehozása és port maping
docker run --name me-fdsz-db -p 33061:3306 -e MYSQL_ROOT_PASSWORD=rootpass -d mysql:latest
docker exec -it me-fdsz-db bash
mysql -u root -p
#új user készítése (nem illik a rootot használni :I )
create user 'marci'@'%' IDENTIFIED by 'userpass';
grant all privileges on * . * TO 'marci'@'localhost';
flush privileges;
#új adatbázis létrehozása
create database ME_FDSZ;
use ME_FDSZ;
create table teszt (id int, value varchar(255));
```
**Kiegészítés ⇒ port mapping**
- `- P` random elérhető portokra publikálja a konténer berlső portjait
- `-p <külső>:<belső>`

#### Csatlakozás kívülről:
`jdbc:mysql://localhost:33061/ME_FDSZ`
