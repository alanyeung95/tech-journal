# Backup

## Logical Backup vs Physical Backup
1. Portability: Easy to move between systems or database versions
2. Human-readable: You can open and inspect the .sql file
3. Restoration takes time (re-executes all SQL statements)
4. No binary data: Doesn’t include transaction logs or internal state

## Logical Backup: mysqldump & cronjob
backup cmd
```
0 2 * * * /usr/bin/mysqldump -u root -p'your_password' my_database > /home/user/backups/my_database_$(date +\%F).sql
```

restore cmd
```
mysql -u root -p my_database < /backups/my_database_backup.sql
```

it creats a .sql file with everything inside.

## Physical Backup
```
sudo systemctl stop mysql
cp -r /var/lib/mysql /path/to/backup/
sudo systemctl start mysql
```
