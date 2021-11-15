# Supermarket Billing System
![Contributors](https://img.shields.io/badge/contributors-6-green)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/code--coverage-99%25-brightgreen)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/BalaBharatRaaj/Supermarket_spd_project)
![GitHub top language](https://img.shields.io/github/languages/top/BalaBharatRaaj/Supermarket_spd_project)
![Version](https://img.shields.io/badge/version-v1.0-informational)
<br/>

The aim of the product was to solve the problems given below,

1. Reduce the number of applications required for the functioning of a Supermarket.
2. Integrate various applications such as billing and inventory management together. 
3. Reduce the time and effort involved in the billing process.
4. Provide an exceptional shopping service to customers. 

## Outline

* Paper bills are now the primary channel of communication between companies and their customers. This leads to humongous waste of paper in turn leading to loss of trees.
* Our team thought to reduce the burden of printing bills in form of paper. So , the best alternative which we could think of was generation of e bill and to automate the whole process of billing. So, is the project E-Billing System.  
* The e-Billing system has the capacity to illustrate and analyse all the major functionalities that are mandatory for a billing system to function in its full capacity. This project aims to complete the whole cycle of billing using technology. 
* Using E – Bill us a boon as it comes with various other advantages. Some of them worth noting are quick, more accurate and concurrent with all the devices.
* This project has 9 frames in which 4 are exclusively present for that shopkeeper and 5 which are exclusively present for the admin of the super market. 
* Donning an attractive UI, it makes the process of billing less cumbersome for the shopkeeper owing to the fact that this project has a lot of functionalities which can be used extensively by the shopkeeper, thus increasing the efficiency of the work.

## Software Requirements Specification (SRS)

The required [SRS](https://drive.google.com/file/d/15LTfXxsUyi1bItV58iJGIOlT4cIogAq4/view?usp=sharing) has been compiled and documented.

## Software Design Document (SDD)

The required [SDD](https://drive.google.com/file/d/1HGPywzkzDTdyjjkVKwP2K5zBoVxDy7Ok/view) has been compiled and documented.

## Code Explanation

The back-end files required are given below,

| File | Description |
| --- | --- |
| `AddProduct.java` | Functions to insert product data into the database |
| `AddProductDatabase.java` | Functions to fetch product data and suggestions for combo boxes |
| `DeleteProductDatabase.java` | Functions to delete product data from the database |
| `EditCustomerDetails.java` | Functions to edit the details of an existing customer |
| `EditProductDatabase.java` | Functions to edit the details of an existing product |
| `ItemsToBeOrdered.java` | Functions to fetch the items having stock below the minimum threshold  |
| `Login_Backend.java` | Functions to verify the password and retrieve the corresponding name |
| `PrintFinalBill.java` | Functions to insert the ordered products into the product sales table and to generate the bill file |
| `ProductSalesHistory.java` | Functions to fetch the products sold in the given date range, month or year |
| `ProductStock.java` | Fetching the stock and details of all existing products |
| `PurchaseHistory.java` | Fetching the products purchased in a particular date range |
| `RegisterCustomer.java` | Functions to add the details of the newly registered customer to the database |

The front-end files required are given below,

| File | Description |
| --- | --- |
| `AddProductFrame` | Add product details to the database|
| `AdminChoice` | Choice for the admin functionalities |
| `BillProductFrame` | Implements actual billing of the system |
| `DeleteEditProductFrame` | Delet/Edit product details |
| `EditCustomerDetailsFrame` | Edit customer details|
| `ItemsToBeOrderFrame` | Display items under the minimum threshold |
| `Login` | Default login page |
| `ProductStockFrame` | Display product details present in the inventory |
| `PurchaseHistoryFrame` | View a customer's purchase history |
| `RegisterCustomerForm` | Register a new customer to the system |
| `SPD_Mini_Project` | Default page of the system |
| `SalesHistoryFrame` | View the history of sales of a given product |
| `ShopkeeperChoice` | Choice for the shopkeeper functionalities |

## Software Tool Requirements

- Front-End Tools - Java, NetBeans GUI 
- Back-End Tools - SQL
- Servers Used - MySQL Server, Apache Tomcat Server
- Operating System - Windows 7+


## Hardware Tool Requirements
- **Server Side**
   - Monitor - 1024 x 768 Resolution
   - Processor - Intel or AMD 2 GHZ
   - RAM - 4 GB
   - Disk Space - 10 GB

- **Client Side**
   - Monitor - 1024 x 768 Resolution
   - Processor - Intel or AMD 1 GHZ
   - RAM - 512 MB
   - Disk Space - 2 GB

## Screen Recording

A [demonstration](https://drive.google.com/file/d/1OPzOpbA6omLGcQaeNYxzhxE-9efFpJJT/view?usp=sharing) of the product illustrating its functionalities was cataloged.  

## Presentation

The required [presentation](https://www.canva.com/design/DAEvUs-ohco/dKStZOIH04M2_h8GHAJ78Q/view?utm_content=DAEvUs-ohco&utm_campaign=designshare&utm_medium=link&utm_source=publishpresent) was made in conjunction with the product developed.

## Members

- 19Z201   -  A. Akash, [@akash1022](https://github.com/akash1022)
- 19Z204   -  Ashwin Rema [@ashwin-rema](https://github.com/ashwin-rema)
- 19Z205   -  Bala Bharat Raaj G S [@BalaBharatRaaj](https://github.com/BalaBharatRaaj/)
- 19Z213   -  Gondi Rajeev [@rajeevgondi](https://github.com/rajeevgondi)
- 19Z221   -  Naresh Kalyanasundaram [@Naresh2002k](https://github.com/Naresh2002k)
- 19Z238   -  Sairam Vaidya M. [@sairam2661](https://github.com/sairam2661/)
