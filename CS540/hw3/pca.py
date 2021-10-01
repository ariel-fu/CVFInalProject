from scipy.linalg import eigh
import os
import numpy

# import numpy as np
# import matplotlib.pyplot as plt


def load_and_center_dataset(filename):
    # TODO: add your code here
    arr = numpy.load(filename)
    mean = numpy.mean(arr, axis=0)
    arr = arr-mean
    return arr


def get_covariance(dataset):
    # TODO: add your code here
    s = dataset
    n = len(s)
    s = numpy.dot(numpy.transpose(s), s)
    s = s/(n-1)
    return s

def get_eig(S, m):
    # TODO: add your code here
    # eigh returns (eigenvalues, eigenvectors)
    # rangeEigenObjects = tuple(m)
    # eigenvaluesEighReturn, eigenvectorsEighReturn = eigh(S, rangeEigenObjects)

    length = len(S)
    eigenvalues, eigenvectors = eigh(S, subset_by_index=[length-m, length-1])

    # eigenvalues, eigenvectors = eigh(S)
    # print("eigenvalues; ", eigenvaluesEighReturn)
    # print("eigenvectors; ", eigenvectorsEighReturn)

    # reverse the order of both eigenvalues and eigenvectors
    eigenvalues = numpy.flipud(eigenvalues)
    # eigenvalues = eigenvalues[:m].copy()
    # print(eigenvalues)
    # # print("values: ", eigenvaluesEighReturn)
    # eigenvectors = numpy.fliplr(eigenvectors)
    # eigenvectors = eigenvectors[:m].copy()
    # print(eigenvectors)
    # print("vectors: ", eigenvectorsEighReturn)
    # get the first m eigenvalues


    # create a diagonal array with the eigenvalues on the diagonal
    valueArray = numpy.diag(eigenvalues)
    # print(valueArray)
    # slice 
    # print("eig value: ", valueArray)

    # create a Numpy array to hold the eigenvectors corresponding to the eigenvalues in the column
    vectorArray = None

    # transpose the eigenvectors to get them as rows, not columns
    # print(eigenvectors)
    # print("-------")
    eigenvectors = eigenvectors.T
    # print("transposed: ", eigenvectors)
    # print("-------")
    # print("vector array: ", vectorArray)

    # loop through all the eigenvalues and add them to the numpy arrays
    isFirst = True
    for eigenvector in eigenvectors:
        # eigenvector is a row vector, transpose and concat the curr vectorArray to the eigenvector
        # print("eig vector: ", eigenvector)
        # eigenvector = numpy.transpose(eigenvector)
        # make it a '2-d' vector
        #eigenvector = numpy.array([eigenvector])
        #eigenvector = numpy.atleast_2d(eigenvector)
        vector = eigenvector.reshape(-1, 1)
        # print("2d? ", vector)
        # transpose teh eigenvector
        # eigenvector.reshape(eigenvector.shape+(1,))
        #eigenvector.transpose()
        # print("eig trans: ", eigenvector)
        if(isFirst):
            vectorArray = vector
            isFirst = False
        else:
            vectorArray = numpy.concatenate((vector, vectorArray), axis=1)
    # print("vector array: ", vectorArray)

    
    # return the nxn Numpy array and the eigenvector Numpy array
    return valueArray, vectorArray

def get_eig_perc(S, perc):
# TODO: add your code here
    # get all the eignvalues and eigenvectors
    eigenvalues, eigenvectors = eigh(S)
    # set the return values and currEigenvalueSum
    valuesPerc = None
    vectorsPerc = None
    currEigenvalueSum = 0

    # for every eigenvalue, check if the variance explained is bigger than perc
    for (eigenvalue, eigenvector) in zip(eigenvalues, eigenvectors):

        # get the variance explained
        currEigenvalueSum += eigenvalue
        print(eigenvalue)
        currExplainedPerc = (eigenvalue/currEigenvalueSum)
        print(currExplainedPerc)

        # transpose the eigenvector
        eigenvector = eigenvector.reshape(-1, 1)
        # if the explainedPerc is bigger than the perc, add to the ret values
        if((currExplainedPerc) > perc):
            if(type(valuesPerc) == None):
                valuesPerc = numpy.array([eigenvalue])
                vectorsPerc = eigenvector
            else:
                valuesPerc = numpy.concatenate((valuesPerc, numpy.array([eigenvalue])))
                vectorsPerc = numpy.concatenate((eigenvector, vectorsPerc), axis=1)
    
    # reverse the order to be in descending order
    eigenvalues = numpy.flipud(eigenvalues)
    # add to a diagonal matrix
    valuesPerc = numpy.diag(eigenvalues)
    return valuesPerc, vectorsPerc


def project_image(img, U):
# TODO: add your code here
    print("project image")


def display_image(orig, proj):
# TODO: add your code here
    print("display image")
