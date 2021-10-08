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
            # TESTER
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
    for data in dataset:
        # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
        currCluster = Cluster(index, [data], index, index, 0, 1)
        if index == 0:
            # set the initial clusters to the first data set
            clusters = [currCluster]
        else:
            # append each data as a new cluster
            clusters = clusters + [currCluster]
        # increment the counter
        index += 1
    # while there are more than 1 clusters left
    listEucDistance = []
    listDistance = []
    clusterCounter = len(clusters)
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
                    # print("cluster2")
                    # print(cluster2.items)
                    index1 = cluster1.index
                    index2 = cluster2.index
                
                    # find the smallest euclidean distance between two current clusters
                    dist = single_linkage(cluster1, cluster2)
                
                    # Cluster = (current index, items, smaller index, larger index, distance between clusters, and total size)
                    newEucDist = Cluster(index, (cluster1.items + cluster2.items), index1, index2, dist, cluster1.size + cluster2.size)               
                    # add to the list of euclidean distance: [i, j, dist, num items]
                    if(len(listEucDistance) == 0):
                        listEucDistance = [newEucDist]
                    else:
                        listEucDistance = listEucDistance+[newEucDist]
                
                # increment the counter
                j += 1
            i += 1
            
        # find the smallest euclidean distance
        smallestCluster = listEucDistance[0]
        for listItem in listEucDistance:
            currDist = listItem.eucDistance
            if(currDist < smallestCluster.eucDistance):
                smallestCluster = listItem
        
        # add the new cluster to the list of clusters and the hacArray
        clusters = (clusters + [smallestCluster])
        clusterCounter += 1
        if(len(hacList) == 0):
            hacList = [smallestCluster]
        else:
            hacList = hacList+[smallestCluster]
        index += 1

        # remove the elements at the two indices
        clusters[smallestCluster.index1] = None
        clusters[smallestCluster.index2] = None
        clusterCounter -= 2
        # restart the distance array
        listEucDistance = []
    
    
    # convert hacList into a numpy array
    counter = 0

# import numpy as np
# arr = np.array([[1,2,3],[4,5,6]])
# row = np.array([7,8,9])
# arr = np.r_[arr,[row]]
# print(arr)
    retNumpyArr = []
    for cluster in hacList:
        # row = [index1, index2, euc distance, # of items]
        index1 = cluster.index1
        index2 = cluster.index2
        eucDistance = cluster.eucDistance
        numItems = cluster.size

        row = np.array([index1, index2, eucDistance, numItems])
        print(row)
        print("items:")
        print(cluster.items)
        print("------------------------------")
        if(len(retNumpyArr) == 0):
            retNumpyArr = row
        else:
            retNumpyArr=np.vstack((retNumpyArr, row))

        # print(cluster)
    # return numpy array
    return retNumpyArr


# Helper method to return the smallest euc distance between two clusters
def single_linkage(cluster1, cluster2):
    # check for invalidity  - nan/inf
    if(not(validate_cluster(cluster1) or not(validate_cluster(cluster2)))):
        return None
    # get the points
    point1 = cluster1.items    
    point2 = cluster2.items        
    # calculate the distance between them
    dist = []
    for p1 in point1:
        for p2 in point2:
            if(len(dist) == 0):
                dist = [math.dist(p1, p2)]
            else:
                dist = dist + [math.dist(p1, p2)]
    # find the smallest euclidien distance
    smallest = dist[0]
    for eucDist in dist:
        if(eucDist < smallest):
            smallest = eucDist

    return smallest


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

def imshow_hac(dataset): 
    """performs single linkage hierarchical agglomerative clustering on the Pokemon with the (x,y) feature representation, and imshow the clustering process."""


## TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
## REMOVE!

def main():
    pokemons = load_data("Pokemon.csv")
    pokemons[1]
    calculate_x_y(pokemons[1])
    pokemons_x_y = []
    print(pokemons[7])
    print(calculate_x_y(pokemons[7]))
    pokemons_x_y = []
    for row in pokemons:
        pokemons_x_y.append(calculate_x_y(row))

    print(hac(pokemons_x_y))

    # pokemons_x_y.append((1, 1))
    # pokemons_x_y.append((2, 1))
    # pokemons_x_y.append((2, 4))
    # pokemons_x_y.append((5, 5))
    # pokemons_x_y.append((3, 2))

    # pokemons_x_y = []
    # pokemons_x_y.append(calculate_x_y(pokemons[1]))
    # pokemons_x_y.append(calculate_x_y(pokemons[2]))
    # pokemons_x_y.append(calculate_x_y(pokemons[3]))
    # pokemons_x_y.append(calculate_x_y(pokemons[4]))
    
    print(hac(pokemons_x_y))

if __name__ =="__main__":
    main()