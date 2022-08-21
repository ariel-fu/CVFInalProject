import csv
import math
import numpy as np
import random as rand
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
    y = stats['Defense'] + stats['Sp. Def'] + stats['HP']
    # couple x and y into a tuple and return it
    ret = (x, y)
    return ret

# helper class of Clusters
class Cluster:
    def __init__(cluster, index, items, index1, index2, eucDistance, size):
        cluster.index = index
        cluster.items = items
        cluster.index1 = index1
        cluster.index2 = index2
        cluster.eucDistance = eucDistance
        cluster.size = size


def hac(dataset):
    """ performs single linkage hierarchical agglomerative clustering on the Pokemon with the (x,y) feature representation, and returns a data structure representing the clustering."""
    clusters = []
    hacList = []
    # place each element in the data set into its own cluster and number them appropriately
    index = 0
    counter = 0
    for data in dataset:
        # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
        currCluster = Cluster(index, [data], index, index, 0, 1)
        # only add the currCluster if it is valid
        if((validate_cluster(currCluster))):
            if counter == 0:
                # set the initial clusters to the first data set
                clusters = [currCluster]
            else:
                # append each data as a new cluster
                clusters = clusters + [currCluster]
            # increment the index/number of individual clusters
            index += 1
        # increment the counter
        counter += 1

    # while there are more than 1 clusters left
    clusterCounter = len(clusters)
    smallestDist = None
    while(clusterCounter > 1):
        i = 0
        # for all current clusters:
        while(i < len(clusters)):
            # get a counter and the list to hold possible clusters
            j = i+1
            while(j < len(clusters)):
                # get the current clusters and their corresponding indices
                cluster1 = clusters[i]
                cluster2 = clusters[j]
                if(not(cluster1 == None or cluster2 == None)): 
                    index1 = cluster1.index
                    index2 = cluster2.index
                    # find the smallest euclidean distance between two current clusters
                    dist, point1, point2 = single_linkage(cluster1, cluster2)
                    # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
                    currDist = Cluster(index, (cluster1.items + cluster2.items), index1, index2, dist, cluster1.size + cluster2.size)
                    # if the smallestDist isn't set yet，set to the current
                    # if the smallestDist is bigger than the currDist, set curr to the smallest
                    if(smallestDist == None or dist < smallestDist.eucDistance):
                        smallestDist = currDist                                   
                # increment the counter
                j += 1
            i += 1
            
       
        # add the new cluster to the list of clusters and the hacArray
        clusters = (clusters + [smallestDist])
        clusterCounter += 1
        if(len(hacList) == 0):
            hacList = [smallestDist]
        else:
            hacList = hacList+[smallestDist]
        index += 1

        # remove the elements at the two indices
        clusters[smallestDist.index1] = None
        clusters[smallestDist.index2] = None
        clusterCounter -= 2
        # restart the distance holder
        smallestDist = None    
    
    # convert hacList into a numpy array
    counter = 0

    retNumpyArr = []
    for cluster in hacList:
        # row = [index1, index2, euc distance, # of items]
        index1 = cluster.index1
        index2 = cluster.index2
        eucDistance = cluster.eucDistance
        numItems = cluster.size

        row = np.matrix([index1, index2, eucDistance, numItems])
        if(len(retNumpyArr) == 0):
            retNumpyArr = row
        else:
            retNumpyArr=np.vstack((retNumpyArr, row))
    # return numpy array
    return retNumpyArr


# Helper method to return the smallest euc distance between two clusters
def single_linkage(cluster1, cluster2):
    # get the points
    point1 = cluster1.items    
    point2 = cluster2.items        
    # calculate the distance between them
    # find the smallest euclidean distance
    dist = []
    for p1 in point1:
        for p2 in point2:
            currDist = math.dist(p1, p2)
            if(len(dist) == 0 or dist[0] > currDist):
                dist = [currDist, p1, p2]
            else:
                dist = dist + [math.dist(p1, p2)]

    return dist[0], dist[1], dist[2]


# Helper method to determine whether the cluster is valid or not
def validate_cluster(cluster):
    pointSet = cluster.items
    for point in pointSet:
        x = point[0]
        y = point[1]
        # check for invalidity  - nan/inf
        if(math.isnan(x) or math.isinf(x) or math.isnan(y) or math.isinf(y)):
            return False
    return True

def random_x_y(m): 
    """ takes in the number of samples we want to randomly generate, and returns these samples in a single structure.""" 
    counter = 0
    randomValues = []
    while(counter < m):
        randX = rand.randrange(1, 360)
        randY = rand.randrange(1, 360)
        randomValues += [[randX, randY]]
        counter += 1
    return randomValues


def imshow_hac(dataset): 
    """performs single linkage hierarchical agglomerative clustering on the Pokemon with the (x,y) feature representation, and imshow the clustering process."""
    plot_hac(dataset)

# helper method that repeats almost exactly what hac did 
def plot_hac(dataset):
    clusters = []
    hacList = []
    # place each element in the data set into its own cluster and number them appropriately
    index = 0
    counter = 0
    for data in dataset:
        # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
        currCluster = Cluster(index, [data], index, index, 0, 1)
        # only add the currCluster if it is valid
        if((validate_cluster(currCluster))):
            if counter == 0:
                # set the initial clusters to the first data set
                clusters = [currCluster]
            else:
                # append each data as a new cluster
                clusters = clusters + [currCluster]
            # increment the index/number of individual clusters
            index += 1
        # increment the counter
        counter += 1
    # while there are more than 1 clusters left
    clusterCounter = len(clusters)
    smallestDist = None
    point1, point2 = (0, 0)
    while(clusterCounter > 1):
        i = 0
        # for all current clusters:
        while(i < len(clusters)):
            # get a counter and the list to hold possible clusters
            j = i+1
            while(j < len(clusters)):
                # get the current clusters and their corresponding indices
                cluster1 = clusters[i]
                cluster2 = clusters[j]
                if(not(cluster1 == None or cluster2 == None)): 
                    index1 = cluster1.index
                    index2 = cluster2.index
                
                    # find the smallest euclidean distance between two current clusters
                    dist, p1, p2 = single_linkage(cluster1, cluster2)
                    # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
                    currDist = Cluster(index, (cluster1.items + cluster2.items), index1, index2, dist, cluster1.size + cluster2.size)
                    # if the smallestDist isn't set yet，set to the current
                    # if the smallestDist is bigger than the currDist, set curr to the smallest
                    if(smallestDist == None or dist < smallestDist.eucDistance):
                        smallestDist = currDist
                        point1 = p1
                        point2 = p2                                   
                # increment the counter
                j += 1
            i += 1
            
       
        # add the new cluster to the list of clusters and the hacArray
        clusters = (clusters + [smallestDist])
        clusterCounter += 1
        if(len(hacList) == 0):
            hacList = [smallestDist]
        else:
            hacList = hacList+[smallestDist]
        index += 1

        # remove the elements at the two indices
        clusters[smallestDist.index1] = None
        clusters[smallestDist.index2] = None
        clusterCounter -= 2

        # plot point1 and point2
        xPoints = np.array([point1[0], point2[0]])
        yPoints = np.array([point1[1], point2[1]])
        plt.scatter(xPoints, yPoints)
        plt.plot(xPoints, yPoints)

        
        plt.pause(0.1)


        # restart the distance holder
        smallestDist = None    
    plt.show()
