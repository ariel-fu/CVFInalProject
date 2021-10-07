import csv
import math
import numpy as np
import matplotlib.pyplot as plt

def load_data(filepath):
    """ takes in a string with a path to a CSV file formatted as in the link above, and returns the first 20 data points (without the Generation and Legendary columns but retaining all other columns) in a single structure. """
    pokemonList = []
    # open the csv file
    with open(filepath, newline="") as f:
        pokemonData = csv.reader(f)
        i = 0
        # get the first 20 elements
        next(pokemonData)
        counter = 0
        for i, row in enumerate(pokemonData):
            pokemonDict = {}
            row = list(row)
            # break if we have 20 pokemon
            if(counter >= 20):
                break

            # cast the #, total, HP, Attack, Defense, Sp. Atk, Sp. Defense, and Speed to integers
            num = int(row[0])
            total = int(row[4])
            hp = int(row[5])
            attack = int(row[6])
            defense = int(row[7])
            spAtk = int(row[8])
            spDef = int(row[9])
            speed = int(row[10])
            pokemonDict = {
                '#': num,
                'Name': row[1],
                'Type 1': row[2], 
                'Type 2': row[3],
                'Total': total,
                'HP': hp,
                'Attack': attack,
                'Defense': defense,
                'Sp. Atk': spAtk,
                'Sp. Def': spDef,
                'Speed': speed
            }
            # append the dictionary onto the list
            pokemonList.append(pokemonDict)
            # increment the counter
            counter +=1
    return pokemonList

def calculate_x_y(stats): 
    """takes in one row from the data loaded from the previous function, calculates the corresponding x, y values for that Pokemon as specified above, and returns them in a single structure."""
    # x = Attack + Sp. Atk + Speed
    x = stats['Attack'] + stats['Sp. Atk'] + stats['Speed']
    # y = Defense + Sp. Def + HP
    y = stats['Defense'] + stats['Sp. Def'] + stats['Speed']
    # couple x and y into a tuple and return it
    ret = (x, y)
    return ret



def hac(dataset):
    """ performs single linkage hierarchical agglomerative clustering on the Pokemon with the (x,y) feature representation, and returns a data structure representing the clustering."""
    clusters = []
    listEucDistance = []
    hacList = []
    # place each element in the data set into its own cluster and number them appropriately
    index = 0
    for data in dataset:
        if index == 0:
            # set the initial clusters to the first data set
            clusters = [(data, index)]
        else:
            # append each data as a new cluster
            clusters = [clusters, (data, index)]
        # increment the counter
        index += 1

    # while there are more than 1 clusters left
    while(len(clusters) > 0):
        i = 0
        # for all current clusters:
        while(i < len(clusters)):
            j = i
            while(j < len(clusters)):
                # get the current clusters and their corresponding indices
                cluster1 = clusters[i]
                index1 = cluster1[1]
                cluster2 = clusters[j]
                index2 = cluster2[1]
                # find the smallest euclidean distance between two current clusters
                dist = single_linkage(cluster1, cluster2)
                newEucDist = [index1, index2, dist]
                # add to the list of euclidean distance: [i, j, dist, num items]
                if(len(listEucDistance) == 0):
                    listEucDistance = [newEucDist]
                else:
                    listEucDistance = [listEucDistance, newEucDist]
                
        # find the smallest euclidean distance
        smallest = listEucDistance[0][2]
        for listItem in listEucDistance:
            currDist = listItem[2]
            if(currDist < smallest):
                smallest = currDist
        # add the information to the return cluster array (index1, index2, and euclidean distance)
        if(len(hacList) == 0):
            hacList = [smallest]
        else:
            hacList = [hacList, smallest]
        # add the new cluster to the list of clusters with index = index+1
        
        # TODO:
        # create a new cluster by merging the values from index1 and index2 into an [(index1), (index2)]
        # add to a new index
        merge = []
        # ARIEL: THIS IS BROKEN!
        clusters = clusters[clusters, (smallest, index)]
        index += 1


        # get the two indices of the elements merged
        index1 = smallest[0]
        index2 = smallest[1]
        # remove the elements at the two indices
        clusters.pop(index1)
        clusters.pop(index2)
    
    # convert hacList into a numpy array
    counter = 0
    for cluster in clusters:
        # get the total number of items
        print("nothing for now")
    # return numpy array
    return None
# Helper method to return the euc distance between two clusters
def single_linkage(cluster1, cluster2):
    # check for invalidity  - nan/inf
    if(not(validate_cluster(cluster1) or validate_cluster(cluster2))):
        return None
    # get the points
    point1 = cluster1[0][0]    
    point2 = cluster2[0][0]        
    # calculate the distance between them
    return math.dist(point1, point2)

# Helper method to determine whether the cluster is valid or not
def validate_cluster(cluster):
    point1 = cluster[0][0]
    x1 = point1[0]
    y1 = point1[1]
    # check for invalidity  - nan/inf
    if(math.isnan(x1) or math.isinf(x1) or math.isnan(y1) or math.isinf(y1)):
        return False
    else:
        return True

def random_x_y(m): 
    """ takes in the number of samples we want to randomly generate, and returns these samples in a single structure.""" 
def imshow_hac(dataset): 
    """performs single linkage hierarchical agglomerative clustering on the Pokemon with the (x,y) feature representation, and imshow the clustering process."""