# -*- coding: utf-8 -*-
"""
Created on Sat Jun 19 17:43:14 2021

@author: Ariel
"""

import pandas as pd #used to read in the revenue file 
import matplotlib.pyplot as plt #for plotting
#to read in shape file and provides high #level interface with #matplotlib library for making maps
import geopandas as gpd
#import locally saved file
revenue = pd.read_excel('C:\Users\Ariel\git\Ariel\hackathon\COVID-19_Data_by_Census_Tract.csv')
#import world shape map externally, no need to have a saved file
world = gpd.read_file('cnty.png')

#view first 5 rows
revenue.head()
#inspect count
revenue.info()
#view first 5 rows
world.head()
#inspect count
world.info()