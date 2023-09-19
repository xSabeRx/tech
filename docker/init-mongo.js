db.getSiblingDB('demo');

db.createUser(
  {
    user: "jarek",
    pwd: "jarek",
    roles: [
      {
        role: "readWrite",
        db: "demo"
      }
    ]
  }
);

db.createCollection('customers');

db.customers.insertMany( [
      { _id: 1, customerType: "AFFILIATE", name: "Jack", lastName: "Logan", createdDate: "2022-08-08" },
      { _id: 2, customerType: "EMPLOYEE", name: "Jack", lastName: "Logan", createdDate: "2022-08-08" },
      { _id: 3, customerType: "NORMAL", name: "Jack", lastName: "Logan", createdDate: "2022-08-08" },
      { _id: 4, customerType: "NORMAL", name: "Jack", lastName: "Logan", createdDate: "2020-08-08" }
   ] );