# Supermarket Billing System
![Contributors](https://img.shields.io/badge/contributors-6-green)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/code--coverage-100%25-brightgreen)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/BalaBharatRaaj/Supermarket_spd_project)
![GitHub top language](https://img.shields.io/github/languages/top/BalaBharatRaaj/Supermarket_spd_project)
![Version](https://img.shields.io/badge/version-v1.0-informational)
![GitHub](https://img.shields.io/github/license/BalaBharatRaaj/Supermarket_spd_project)
<br/>

The aim of the product was to solve the problems given below,

1. Reduce the number of applications required for the functioning of a Supermarket.
2. Integrate various applications such as billing and inventory management together. 
3. Reduce the time and effort involved in the billing process.
4. Provide an exceptional shopping service to customers. 

## Outline
The development of the required product was divided into three phases,

1. ### Discovery Phase
     - The first step was to identify the key stakeholders - the Administrator, and the various Shop Workers.
     - The next step was to research the existing applications and identify their drawbacks, to gain a fundamental understanding on the product required by the Supermarket.
     - The final step was to collaborate all the findings and generate a blueprint for the product required.
     
2. ### Strategy Phase
     - The first step was to identify the overall description of the product based on the discovery phase.
     - The next step was to identify the external interface requirements, functional requirements, non-functional requirements and the system features.
     - An additional step to identify any other requirements necessary was also considered.
     - The final step was to collaborate all the observations made into a Software Requirements Specification document (SRS).

3. ### Design & Development Phase
     - The first step was to come up with a blueprint for the design - this was done by constructing various UML diagrams for the SRS created in the strategy phase. 
     - These diagrams were collaborated into the Software Design Document (SDD).
     - The development was carried out in the NetBeans IDE environment primarily, and was integrated with the mySQL database via the phpmyadmin administration tool.
     - The connections were made using the mySQL server and the Apache Tomcat server.
     - Summarizing the development, the product has nine frames out of which four are present for the shop worker, and five for the admin of the super market. 
     - The UI has been geared to be simple and pleasant to make the User Experience pleasing, less cumbersome and also to increase the efficiency of the work done.

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
