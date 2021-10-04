from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt


def load_and_center_dataset(filename):
    # TODO: add your code here
    arr = np.load(filename)
    mean = np.mean(arr, axis=0)
    arr = arr-mean
    return arr


def get_covariance(dataset):
    # TODO: add your code here
    s = dataset
    n = len(s)
    s = np.dot(np.transpose(s), s)
    s = s/(n-1)
    return s

def get_eig(S, m):
    # TODO: add your code here

    length = len(S)
    eigenvalues, eigenvectors = eigh(S, eigvals=[length-m, length-1])

    # reverse the order of the eigenvalues
    eigenvalues = np.flipud(eigenvalues)

    # create a diagonal array with the eigenvalues on the diagonal
    valueArray = np.diag(eigenvalues)
    # create a np array to hold the eigenvectors corresponding to the eigenvalues in the column
    vectorArray = None

    # transpose the eigenvectors to get them as rows, not columns
    eigenvectors = eigenvectors.T

    # loop through all the eigenvalues and add them to the np arrays
    isFirst = True
    for eigenvector in eigenvectors:
        # eigenvector is a row vector, transpose and concat the curr vectorArray to the eigenvector
        vector = eigenvector.reshape(-1, 1)
        if(isFirst):
            vectorArray = vector
            isFirst = False
        else:
            vectorArray = np.concatenate((vector, vectorArray), axis=1)

    
    # return the nxn np array and the eigenvector np array
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
        # if the explainedPerc is bigger than the perc, add 1 to the number of eigenobjects to retrieve
        if((currExplainedPerc) > perc):
            numEigenObjects+=1
    # get the first x num eigenvalues and eigenvectors
    valuesPerc, vectorsPerc = get_eig(S, numEigenObjects)
    return valuesPerc, vectorsPerc



def project_image(img, U):
# TODO: add your code here
    # keep track of the sum
    summation = None
    U = np.transpose(U)
    isFirst = True
    # calculate the alpha
    alphaList = np.dot(U, img)
    # multiply every alpha by every u in U
    for alpha, u in zip(alphaList, U):
        # add to the total sum
        if(isFirst):
            summation = alpha*u
            isFirst = False
        else:
            summation += alpha*u
    
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
    
    # imshow() to render orig image in 1st subplot and proj in 2nd subplot
    origShow = ax1.imshow(orig, aspect='equal')
    fig.colorbar(origShow, ax=ax1)
    projShow = ax2.imshow(proj, aspect='equal')    
    fig.colorbar(projShow, ax=ax2)

    # render the plots
    plt.show()


