# -*- coding: utf-8 -*-
"""
Created on Sat Jun  5 16:40:26 2021

@author: shrey
"""
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

data = pd.read_csv(r'C:\Users\Ariel\git\Ariel\hackathon maps\COVID-19_Data_by_Census_Tract.csv',low_memory=False)
#County data 

columns = ['COUNTY', 'POSITIVE', 'NEGATIVE', 'DEATHS', 'HOSP_YES', 'HOSP_NO']
needed_data = data[columns]#Data with only these columns

needed_data.loc[(needed_data['DEATHS']==-999),'DEATHS']=0
#replace -999 with 0 in the DEATHS column


needed_data.sort_values("COUNTY", inplace = True)
#Sort in ascending alphabetical order of county

needed_data.drop_duplicates(subset ="COUNTY", keep = 'last', inplace = True)
#Use only one value from each county

import geoplot as gplt
import geopandas as gpd
import geoplot.crs as gcrs
usa_counties = gpd.read_file(r'C:\Users\Ariel\git\Ariel\hackathon maps\cb_2018_us_county_500k.shp')
counties = gpd.read_file(r'C:\Users\Ariel\git\Ariel\hackathon maps\Wisconsin_State_Boundary_24K.shp')
#Reading the shp file to create a geo data frame  - usa_counties(to help plot the map)

wisco = usa_counties["STATEFP"]=='55'
wisc_counties = usa_counties[wisco]
#Extracting only wisconsin counties (whose statefp = 55)
#Storing the wisconsin counties in another geo dataframe - wisc_counties

covid_counties = wisc_counties.merge(needed_data, left_on= "NAME", right_on = "COUNTY")
#Merging the csv dataset - needed_data from above and the wisc_counties geo dataframe together while having the 
#'COUNTY' as a common column.

variable = 'POSITIVE'
# set the range for the choropleth
vmin, vmax = 120, 220
# create figure and axes for Matplotlib
fig, ax = plt.subplots(1, figsize=(10, 6))

covid_counties.plot(column=variable, cmap='Blues', linewidth=0.8, ax=ax, edgecolor='0.8')
#Creates a simple plot with POSITIVE cases as a factor. Dark Blue county - more #positive cases.

import plotly as py
import plotly.express as px


extra_data = pd.read_csv(r'C:\Users\Ariel\git\Ariel\hackathon maps\time_series_covid_19_confirmed_US.csv')
#Reading another dataset that has latitudes and longitudes of all US counties.






ata = extra_data['Province_State']=='Wisconsin'
#Extracting only wisonsin counties
extra_data = extra_data[wisco_data]
#Stores it in extra_data

new_columns = ['Admin2', 'Province_State','Lat', 'Long_']
extra_data = extra_data[new_columns]
#Extract only these columns and store in extra_data again.
#Extra data now has latitiudes and longitudes of wisconsin counties only.


new_counties = covid_counties.merge(extra_data, left_on = 'COUNTY', right_on = 'Admin2')
#Merge extra_data with the previous geo dataframe(covid_counties) to create a new geo data frame.
#This time with location coordinates.

new_counties.drop(new_counties.index[new_counties['Lat'] == '0'], inplace = True)
#Invalid entries are dropped

#ERRORS START HERE - generating a choropleth
fig = px.choropleth(
    new_counties, #Dataframe
    lat= 'Lat', #Latitude
    lon= 'Long_', #Longitude
    locationmode='ISO-3',
    color= 'POSITIVE', #Values to be color coded
    hover_name= 'COUNTY', #Text to be displayed in Bold upon hover
    hover_data= ['POSITIVE','NEGATIVE','DEATHS'] #Extra text to be displayed in Hover tip

)

fig.update_layout(
    title_text =   " COVID-19 Spread in the World up to 25 April 2020",
    title_x = 0.5,
    geo= dict(
        showframe= False,
        showcoastlines= False,
        projection_type = 'equirectangular'
    )
)

fig.show()