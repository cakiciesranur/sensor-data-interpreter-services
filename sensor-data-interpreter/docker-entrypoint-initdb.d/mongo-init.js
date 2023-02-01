db = db.getSiblingDB('admin');
// move to the admin db - always created in Mongo
db.auth('rootUser', 'rootPassword');
// log as root admin if you decided to authenticate in your docker-compose file...
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
if (db.getUser("consumerUser") == null) {
    db.createUser({
        user: "consumerUser", pwd: "password", roles: [
            {role: "readWrite", db: "sensorDataDb"},
            {role: "dbAdmin", db: "sensorDataDb"}
        ]
    });
}
print("User created in new db");