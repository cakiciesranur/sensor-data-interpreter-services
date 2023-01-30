print("======databases in mongo in the beginning===");
print(db.adminCommand('listDatabases'));
db = db.getSiblingDB('admin');
// move to the admin db - always created in Mongo
db.auth('rootUser', 'rootPassword');
// log as root admin if you decided to authenticate in your docker-compose file...
print("======Users in Admin======");
print(db.getUsers());
if (db.getUser("consumerUser") == null) {
    db.createUser({
        user: "consumerUser", pwd: "password", roles: [
            {role: "readWrite", db: "sensorDataDb"},
            {role: "dbAdmin", db: "sensorDataDb"}
        ]
    });
}
db = db.getSiblingDB('sensorDataDb');
// create and move to your new database
print("======Started Adding the User to sensorDataDb");
if (db.getUser("consumerUser") == null) {
    db.createUser({
        user: "consumerUser", pwd: "password", roles: [
            {role: "readWrite", db: "sensorDataDb"},
            {role: "dbAdmin", db: "sensorDataDb"}
        ]
    });
}
print("User created");
print("======Users in SensordataDb======");
print(db.getUsers());
print("======databases in mongo in the end===");
print(db.adminCommand('listDatabases'));