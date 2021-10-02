from scipy.linalg import eigh
import os
import numpy

# import numpy as np
import matplotlib.pyplot as plt


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

    # calculate the total sum of eigenvalues
    totEigenvalueSum = 0
    for eigenvalue in eigenvalues:
        totEigenvalueSum += eigenvalue
    
    numEigenObjects = 0
    # for every eigenvalue, check if the variance explained is bigger than perc
    for (eigenvalue, eigenvector) in zip(eigenvalues, eigenvectors):

        # get the variance explained
        currExplainedPerc = (eigenvalue/totEigenvalueSum)
        # print("curr eigen val: ", eigenvalue)
        # print("curr sum: ", currEigenvalueSum)        
        # print("curr perc: ", currExplainedPerc)
        # print("--------------")

        # if the explainedPerc is bigger than the perc, add 1 to the number of eigenobjects to retrieve
        if((currExplainedPerc) > perc):
            numEigenObjects+=1

            # print("curr explained perc: ", currExplainedPerc)
            # print("per: ", perc)
            # print("-====================-")
            # transpose the eigenvector

            # print("pretransposed: ", eigenvector)
            # eigenvector = eigenvector.reshape(-1, 1)
            # print("transposed: ", eigenvector)
            # print("==============")
            # if(isFirst):
            #     valuesPerc = numpy.array([eigenvalue])
            #     vectorsPerc = eigenvector
            #     isFirst = False
            # else:
            #     valuesPerc = numpy.concatenate((valuesPerc, numpy.array([eigenvalue])))
            #     vectorsPerc = numpy.concatenate((eigenvector, vectorsPerc), axis=1)
            # print(valuesPerc)
    
    # reverse the order to be in descending order
    # eigenvalues = numpy.flipud(valuesPerc)
    # # add to a diagonal matrix
    # valuesPerc = numpy.diag(eigenvalues)
    valuesPerc, vectorsPerc = get_eig(S, numEigenObjects)
    # print(valuesPerc)
    return valuesPerc, vectorsPerc



def project_image(img, U):
# TODO: add your code here
    # get alpha = (U^T)*dot*(img)
    # transU = numpy.transpose(U)
    # alpha = numpy.dot(transU, img)
    # # dot alpha with the U matrix
    # return numpy.dot(alpha, numpy.transpose(U))

    # keep track of the sum
    summation = None
    U = numpy.transpose(U)
    isFirst = True
    alphaList = numpy.dot(U, img)
    for alpha, u in zip(alphaList, U):
        if(isFirst):
            summation = alpha*u
            isFirst = False
        else:
            summation += alpha*u
    # for u in U:
    #     # print("beofre transp: ", u)
    #     # u = u.reshape(-1, 1)
    #     # img = img.reshape(-1, 1)
    #     # print("after transp: ", u)
    #     alpha = numpy.dot(u, img)
    #     print(alpha)
    #     print(u)
    #     summation += alpha

    return summation



def display_image(orig, proj):
# TODO: add your code here
    # reshape orig and proj to be 32 x 32
    orig = orig.reshape(32, 32)
    proj = proj.reshape(32, 32)

    orig = orig.transpose()
    proj = proj.transpose()
    # create a figure with one row of two subplots
    fig, (ax1, ax2) = plt.subplots(nrows=1, ncols=2)
    # set the titles
    ax1.set_title('Original')
    ax2.set_title('Projection')


    # get the ratio to make the colorbar the same height as the plots
    # ratio1 = (0.047)*(ax1.bbox.height/ax1.bbox.width)
    # imshow() to render orig image in 1st subplot and proj in 2nd subplot
    origShow = ax1.imshow(orig, aspect='equal')
    fig.colorbar(origShow, ax=ax1)
    # fig.colorbar(origShow, ax=ax1, fraction=ratio1)

    # ratio2 = (0.047)*(ax2.bbox.height/ax2.bbox.width)
    projShow = ax2.imshow(proj, aspect='equal')    
    fig.colorbar(projShow, ax=ax2)
    # fig.colorbar(projShow, ax=ax2,fraction=ratio2)
    # render the plots
    plt.show()


