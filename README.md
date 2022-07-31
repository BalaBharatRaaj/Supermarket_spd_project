# Yorozuya Submission - Flipkart Grid 4.0 (Social Media Trends)
![Contributors](https://img.shields.io/badge/contributors-3-green)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage](https://img.shields.io/badge/code--coverage-100%25-brightgreen)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/sairam2661/Flipkart-grid-4.0)
![GitHub top language](https://img.shields.io/github/languages/top/sairam2661/Flipkart-grid-4.0)
![Version](https://img.shields.io/badge/version-v1.0-informational)
![GitHub](https://img.shields.io/github/license/sairam2661/Flipkart-grid-4.0)
<br/>

## **Important: Due to size issues, the demonstration can be found [here](https://drive.google.com/file/d/1cKAmJP6zvsUwpO4c6p1btQcW2rk2Ia4V/view?usp=sharing).**  
<br/>

The aim of the solution developed was to solve the problems given below,

1. Identification of trends from social media.
2. Mapping trends with Flipkart products.

## Outline
The development of the proposed solution was divided into three parts,

1. ### Automated Bots for Scraping Required Images and Metadata
     - These bots were used to perform asynchronous scraping of images and metadata from various platforms such as Instagram, Pinterest, etc.
     
2. ### Trend Identification and Image Classification
     - This tier was concerned with handling the images obtained in the previous stage, and identifying the fashion trends involved in them, and hence classifying the images.
     - This was achieved using deep neural networks (based on VGG16 Architecture) to identify the trends present in the data collected.

3. ### Flipkart Relevant Smart Mapper
     - This was built to return a series of results/recommendations for a search query in a using query construction techniques, with the result being mapped the to the attributes present on the Flipkart website. 
     - This was built using a combination of Fuzzy techniques, Rule based learning and the Wordnets library to map a given phrase to Flipkart relevant format.

## Code Explanation

The files present are described below,

| File | Description |
| --- | --- |
| `BlogScraping.ipynb` | Jupyter Notebook used for scraping data from blogs |
| `Final_Flipkart.ipynb` | Jupyter Notebook displaying the end result of part 2 |
| `Flipkart_Deliverable_2.ipynb` | Jupyter Notebook for the second deliverable |
| `InstaScrapping.ipynb` | Jupyter Notebook for scraping data from Instagram |
| `Yorozuya Flipkart Grid PPT.pdf` | Final Presentation for Flipkart SD Grid 4.0 |

The folders present are described below,

| Folder | Description |
| --- | --- |
| `Dataset_Scraper` | Contains the files required for scraping the dataset |
| `Preprocessing` | Contains the files required for preprocessing in the second stage |

## Screen Recording

A [demonstration](https://drive.google.com/file/d/1cKAmJP6zvsUwpO4c6p1btQcW2rk2Ia4V/view?usp=sharing) of the deliverables illustrating their functionalities was cataloged.  

## Presentation

The required [presentation](https://drive.google.com/file/d/13_X8sXRJevog18nj_edrX5-BJ853lpur/view?usp=sharing) was made in conjunction with the product developed.

## Members

- Sairam Vaidya M. [@sairam2661](https://github.com/sairam2661)
- Guna M [@Guna2K](https://github.com/Guna2K)
- Lohith Sowmiyan P S [@LohithSowmiyanPS](https://github.com/LohithSowmiyanPS)
