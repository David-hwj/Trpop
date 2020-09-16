# Trpop
Final Hacthon of Citi NEUEDA Training

**Team member:** Huang Weijiang,Huang Lei,Liu Xinya,Lin Wenze

1 INTRODUCTION

1.1 Problem Description
According to the program introduction, we are trying to design an application for the
individual users. 
To manage personal finance, users need to import transactions from other account interfaces, 
assign transactions to various categories to track the current month’s expenditure and set their 
individual monthly budget for every category they created. 

1.2 Solutions
In order to solve the above requirements, we design the following solutions.
The main functions that need to be implemented are as follows:
1. To import transactions from users’ accounts – current / checking / credit cards etc into the 
app, and track their spending by allocating transactions into the following categories.
2. Predefined categories：Utilities, Travel, Groceries, Drinks, Rent, Cell Phone, Car Payments, 
Fun Money. Uses are allowed to add / remove or change categories to suit their individual 
needs.
3. Set the budget for each category every month, and users can see the current expenditure & 
budget of each category.
The priority of requirements is shown in the table below:
Feature A import transactions from other account interfaces High Priority
Feature B allocate transactions into the following categories High Priority
Feature C CRUD categories Medium Priority Medium Priority
Feature D Set the budget, show the current expenditure & 
budget of each category
Low Priority

2 USER FUNCTION
Assuming that the user is logging in----
The system provides users with multi-function entrances:
1. Import daily consumption records from other accounts, such as cash, bank card, credit card, 
check, etc. As usual, the way we adopt is to save these data into a CSV file and then directly 
import it into the system.
2. Users can define their own categories according to their actual needs, or modify or delete the 
existing categories according to the actual situation. For each consumption, users can select a 
category for it, create a new consumption record and submit it to the background for saving.
3. Users can set a monthly budge for each category and it can be modified at any time.
4. Users can also check the current expenditure situation and check their monthly budge.
Fig.1 the User Function diagram

3 IMPLEMENT

3.1 Technologies used
1. Spring Boot: Spring Boot is an open source Java based framework for creating microservices.
Using it to develop RestfulApi can improve productivity and shorten development time.
2. MongoDB: MongoDB is a product between relational database and non-relational database. 
It supports a very loose data structure, which is similar to JSON's bson format, so it can store 
more complex data types. 
3. Vue.js: Vue is a progressive frame for the construction of the user interface. 
4. Git: Git is an open source distributed version control system that can effectively and quickly 
handle version management from very small to very large projects.

3.2 Domain classes
This is the domain class UML diagram. In this diagram, we have 4 classes including Category, 
Spend, Budget, Bill. 
1. Category class has two attributes -- id and type. Type means the type of the category and is 
String type. 
2. Spend class has4 attributes -- String, payment, Date, note. Payment means the payment 
amount of the transaction, and note means the name of the transaction. 
3. Budget class has int, String, budget, Date 4 attributes. Budget means the budget of the 
category. 
4. Bill class has 2 attributes -- accountType and file, and 2 methods -- Bill( ) and getData( ). 
Bill( ) methods is the constructor and the getData( ) method can return the data from a csv 
file with the List type. 

3.3 Database Design
--Budget table
--Budget: [id, month, type, budget]
Budget
Field Description type of data
id Primary key int
month Month of transaction Date
type Type String
budget The budget of the category double
--Categories table
--Categories: [id, type]
Categories
Field Description type of data
id Primary key int
type Type of the category String
--Spend table
--Spend: [id, type, payment, date, note]
Spend
Field Description type of data
id Primary key int
type The category of the transaction String
payment Payment amount of the transaction Double
date Date of transaction Date
note Name of the transaction String

3.4 Rest API 
We have three RestControllers to manage the requests from client. 
1. SpendRestController
This controller will receive all requests from "/spend". It uses SpendService to process specific 
request.
And the SpendService has four methods to implement CRUD.
+ getByDate(Date,Date): List<Spend> -- to get all Spend recorders from a date to another date.
+ putSpend(Spend): Spend -- to update a specific recoder in MongoDB.
+ postSpend(Spend): Spend -- to add a recoder in MongoDB
+ delSpend(Spend): Spend -- to delete a specific recoder in MongoDB
2. CategoryRestController
This controller will receive all requests from "/category". It uses CategoryService to process specific 
request.
And the CategoryService has four methods to implement CRUD.
+ getAll(): List<Category> -- to get all Category recoders from MongoDB.
+ putCategory(Category): Category -- to update a specific recoder in MongoDB.
+ postCategory(Category): Category -- to add a recoder in MongoDB
+ delCategory(Category): Category -- to delete a specific recoder in MongoDB
3. BudgetRestController
This controller will receive all requests from "/budget". It uses BudgetService to process specific 
request.
And the BudgetService has four methods to implement CRUD of budget.
+ getByMonth(Date): List<Budget> -- to get all Budget recoders by Month from MongoDB.
+ putBudget(Budget): Budget -- to update a specific recoder in MongoDB.
+ postBudget(Budget): Budget -- to add a recoder in MongoDB
+ delBudget(Budget): Budget -- to delete a specific recoder in MongoDB
