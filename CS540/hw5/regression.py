import numpy as np
from matplotlib import pyplot as plt
import csv
import math

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_dataset(filename):
    """
    TODO: implement this function.

    INPUT: 
        filename - a string representing the path to the csv file.

    RETURNS:
        An n by m+1 array, where n is # data points and m is # features.
        The labels y should be in the first column.
    """
    file = open(filename)
    bodyfat_reader = csv.reader(file)
    next(bodyfat_reader)
    dataset = None
    isFirst = True
    for row in bodyfat_reader:
        numpyRow = []
        # skip first column
        counter = 1
        # get the rest of the column's elements
        while(counter < len(row)):
            if(counter == 1):
                numpyRow = [float(row[counter])]
            else:
                numpyRow.append(float(row[counter]))
            counter += 1
        # add curr to the total array
        if isFirst:
            isFirst = False
            dataset = numpyRow
        else:
            dataset = np.vstack((dataset, numpyRow))
    print(dataset)
    return dataset


def print_stats(dataset, col):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        col     - the index of feature to summarize on. 
                  For example, 1 refers to density.
    RETURNS:
        None
    """
    # get the values at col
    colValues = []
    for data in dataset:
        colData = data[col]
        if(len(colValues)== 0):
            colValues = [colData]
        else:
            colValues.append(colData)
    length = len(colValues)

    runningValue = 0
    for value in colValues:
        runningValue += value
    sampleMean = runningValue/length

    runningValue = 0
    for value in colValues:
        runningValue += (value-sampleMean)**2
    runningValue = (1/(length-1))*runningValue
    sampleStandardDeviation = math.sqrt(runningValue)

    print(length)
    print('{:0.2f}'.format(sampleMean))
    print('{:0.2f}'.format(sampleStandardDeviation))
    pass


def regression(dataset, cols, betas):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array = y_i
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.
        betas   - a list of elements chosen from [beta0, beta1, ..., betam]
    RETURNS:
        mse of the regression model
    """

    mse = 0
    # y = first column in dataset
    bodyFatDataset = dataset[:,0]
    datasetCounter = 0
    
    for y in bodyFatDataset:
        runningSum = calculateSummation(betas, cols, dataset, datasetCounter)
        # increment the counter
        datasetCounter += 1        
        # add beta_0
        runningSum += betas[0]
        # subtract y_i
        runningSum -= y
        # square the result
        runningSum *= runningSum
        # add to mse
        mse += runningSum
    # divide by the number of elements in bodyFatDataset to normalize
    mse /= len(bodyFatDataset)
    return mse




def gradient_descent(dataset, cols, betas):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.
        betas   - a list of elements chosen from [beta0, beta1, ..., betam]

    RETURNS:
        An 1D array of gradients
    """
    grads = None
    # get the set of ys
    bodyFatDataset = dataset[:,0]
    
    isFirst = True
    xIncrement = -1
    for beta in betas:
        datasetCounter = 0
        summation = 0
        for y in bodyFatDataset:
            currValue = 0
            if(isFirst):
                x_im = 1
            else:
                x_im = dataset[datasetCounter][cols[xIncrement]]
            # calculate the summation
            currValue += calculateSummation(betas, cols, dataset, datasetCounter)
            # subtact y and add beta_0
            currValue -= y
            currValue += betas[0]
            # multiply by x_im
            currValue *= x_im
            summation += currValue
            datasetCounter += 1
        
        summation *= (2/len(bodyFatDataset))
        xIncrement += 1
        # add to the numpy array
        if(isFirst):
            grads = np.array(summation)
            isFirst = False
        else:
            grads = np.append(grads, summation)
    return grads


def calculateSummation(betas, cols, dataset, datasetCounter):
    summation = 0
    betaCounter = 1
    for x in cols:
        # x_im = dataset[i][x] & beta_m = betas[x]
        x_im = dataset[datasetCounter][x]
        beta_m = betas[betaCounter]
        betaXProduct = x_im * beta_m
        summation += betaXProduct
        betaCounter += 1
    return summation



def iterate_gradient(dataset, cols, betas, T, eta):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.
        betas   - a list of elements chosen from [beta0, beta1, ..., betam]
        T       - # iterations to run
        eta     - learning rate

    RETURNS:
        None
    """

    # save the previous set of Betas
    prevBetas = betas
    stringArray = []
    i = 1
    while i <= T:
        # get the gradient
        gradient = gradient_descent(dataset, cols, prevBetas)
        gradientIndex = 0
        currBetas = []
        for beta in prevBetas:
            currBeta = beta-(eta*gradient[gradientIndex])
            if(len(currBetas) == 0):
                currBetas = [currBeta]
            else:
                currBetas += [currBeta]
        
            # increment the gradient index
            gradientIndex += 1

        # get the MSE
        
        MSE = regression(dataset, cols, currBetas)
        stringArray = [i, MSE]   
        stringArray += currBetas
        isFirst = True
        # format print
        for string in stringArray:
            if(isFirst):
                print(string, end = ' ')
                isFirst = False
            else:
                print('{:0.2f}'.format(string), end = ' ')
        # add a new line
        print()
        
        # set up for next iteration
        prevBetas = currBetas
        i += 1
    pass


def compute_betas(dataset, cols):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.

    RETURNS:
        A tuple containing corresponding mse and several learned betas
    """
    betas = []
    xTranspose = []
    for col in cols:
        newRow = dataset[:,col]
        if(len(xTranspose) == 0):
            xTranspose = np.asmatrix(newRow)
        else:
            xTranspose = np.vstack((xTranspose, newRow))
    # append a row of 1s to the top
    onesRow = np.ones(xTranspose.shape[1])
    xTranspose = np.vstack((onesRow, xTranspose))
    x = xTranspose.getT()
    # x^T * x
    currBetas = np.matmul(xTranspose, x)
    # (x^T * x)^-1
    currBetas = currBetas.I
    # (x^T * x)^-1 * x^T
    currBetas = np.matmul(currBetas, xTranspose)
    y = dataset[:,0]
    y = np.vstack(y)
    # (x^T * x)^-1 * x^T * y
    currBetas = np.matmul(currBetas, y)
    currBetas = (np.asarray(currBetas)).flatten()
    mse = regression(dataset, cols, currBetas)
    for beta in currBetas:
        betas += [beta]
    return (mse, *betas)


def predict(dataset, cols, features):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.
        features- a list of observed values

    RETURNS:
        The predicted body fat percentage value
    """
    result = 0
    betas = compute_betas(dataset, cols)
    betaCounter = 2
    for feature in features:
        result += feature * betas[betaCounter]
        betaCounter += 1
    # add b_0
    result += betas[1]
    return result


def synthetic_datasets(betas, alphas, X, sigma):
    """
    TODO: implement this function.

    Input:
        betas  - parameters of the linear model
        alphas - parameters of the quadratic model
        X      - the input array (shape is guaranteed to be (n,1))
        sigma  - standard deviation of noise

    RETURNS:
        Two datasets of shape (n,2) - linear one first, followed by quadratic.
    """
    linearDataset = []
    quadraticDataset = []

    for x in X:       
        z = np.random.normal(0, sigma) 
        beta_0 = betas[0]
        beta_1 = betas[1]
        newDatapoint = beta_0+(beta_1*x)+z
        newRow = np.append(newDatapoint, x)
        if(len(linearDataset) == 0):
            linearDataset = np.array([newRow])
        else:
            linearDataset = np.vstack((linearDataset, np.array([newRow])))
    
    for x in X:        
        z = np.random.normal(0, sigma)
        alpha_0 = alphas[0]
        alpha_1 = alphas[1]
        newDatapoint = alpha_0+(alpha_1*(x**2))+z
        newRow = np.append(newDatapoint, x)
        if(len(quadraticDataset) == 0):
            quadraticDataset = np.array([newRow])
        else:
            quadraticDataset = np.vstack((quadraticDataset, np.array([newRow])))


    return linearDataset, quadraticDataset


def plot_mse():
    from sys import argv
    if len(argv) == 2 and argv[1] == 'csl':
        import matplotlib
        matplotlib.use('Agg')

    # TODO: Generate datasets and plot an MSE-sigma graph
    # X = np.random.uniform(-100, 100, 1000)
    # TEST
    X = np.random.uniform(-100, 100, 1000)
    betas = [6, 4]
    alphas = [1, 3]

    sigmas = [10**-4, 10**-3, 10**-2, 10**-1, 1, 10**1, 10**2, 10**3, 10**4, 10**5]
    linX = []
    quadX = []
    for sigma in sigmas:
        linear, quadratic = synthetic_datasets(betas, alphas, X, sigma)
        linMSE = compute_betas(linear, cols = [1])
        
        print("sigma: ", sigma)
        print("---------------")
        print("linear values:")
        print(linear)
        print("MSE: ", linMSE[0])
        print("beta_0: ", linMSE[1])
        print("beta_1: ", linMSE[2])

        quadMSE = compute_betas(quadratic, cols = [1])
        
        print("---------------")
        print("quadratic values:")
        print(quadratic)
        print("MSE: ", quadMSE[0])
        print("beta_0: ", quadMSE[1])
        print("beta_1: ", quadMSE[2])
        print("---------------")
        
        if(len(linX) == 0):
            linX = [linMSE[0]]
            quadX = [quadMSE[0]]
        else:
            linX += [linMSE[0]]
            quadX += [quadMSE[0]]
    plt.plot(sigmas, linX, '-o')
    plt.plot(sigmas, quadX, '-o')
    plt.yscale("log")
    plt.xscale("log")

    plt.savefig('mse.pdf')

    





    # # TEST
    # # X = np.random.randint(-100, 100, 1000)
    # X = np.random.uniform(-100, 100, 2)
    # betas = [1, 2]
    # alphas = [2, 2]

    # sigmas = [10**-4, 10**-3, 10**-2, 10**-1, 10**0, 10**1, 10**2, 10**3, 10**4, 10**5]
    # linearMSE = []
    # quadraticMSE = []
    # for sigma in sigmas:
    #     linearOrig, quadraticOrig = synthetic_datasets(betas, alphas, X, sigma)
    #     # TEST
    #     # make the original column to the first
    #     print(linearOrig)
    #     linearOrig = linearOrig.transpose()
    #     holder = linearOrig[0]
    #     linear = linearOrig[1]
    #     linear = np.vstack((linear, holder))
    #     linear = linear.transpose()
    #     # TEST
    #     print(linear)

    #     print(quadraticOrig)
    #     quadraticOrig = quadraticOrig.transpose()
    #     holder = quadraticOrig[0]
    #     quadratic = quadraticOrig[1]
    #     quadratic = np.vstack((quadratic, holder))
    #     quadratic = quadratic.transpose()
    #     # TEST
    #     print(quadratic)
        
    #     linear = compute_betas(linear, cols = [1])
    #     quadratic = compute_betas(quadratic, cols = [1])
    #     if(len(linearMSE) == 0):
    #         linearMSE = [linear[0]]
    #         quadraticMSE = [quadratic[0]]
    #     else:
    #         linearMSE += [linear[0]]
    #         quadraticMSE += [quadratic[0]]

    # # x-components = sigma values
    # xpoints = np.array(sigmas)
    # # y-components = linearMSE/quadraticMSE
    # yLinear = np.array(linearMSE)
    # yQuadratic = np.array(quadraticMSE)
    # fig = plt.figure()

    # # plot out both components using '-o'
    # plt.plot(xpoints, yLinear, '-o')
    # plt.plot(xpoints, yQuadratic, '-o')
    # plt.yscale("log")
    # plt.xscale("log")
    # plt.pause(1)
    # plt.show()




def main():
    # dataset = get_dataset('bodyfat.csv')
    # # regression(dataset, cols=[2,3], betas=[0,0,0])
    # # regression - Canvas
    # print(regression(dataset, cols=[2,3,4], betas=[0,-1.1,-0.2,3]))
    # # gradient descent - Canvas
    # print(gradient_descent(dataset, cols=[2,3], betas=[0,0,0]))
    # print(gradient_descent(dataset, cols=[1,4], betas=[0,0,0]))
    # # iterate gradient - Canvas
    # print(iterate_gradient(dataset, cols=[1,8], betas=[400,-400,300], T=10, eta=1e-4))
    # print(iterate_gradient(dataset, cols=[1,4], betas=[400,-400,10], T=5, eta=1e-4))
    # # compute betas - Canvas
    # print(compute_betas(dataset, cols=[1,2]))
    # print(compute_betas(dataset, cols=[1,2,8,9]))
    # # predict - Canvas
    # print(predict(dataset, cols=[1, 2], features=[1.0708, 23]))
    # # synthetic datasets - Canvas
    # print(synthetic_datasets(np.array([0,2]), np.array([0,1]), np.array([[4], [5]]), 1))

    dataset = np.array([[2, 4], [2, 6]])
    print(compute_betas(dataset, cols=[1]))

if __name__ == '__main__':
    ## TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
    ## REMOVE

    main()

    ### DO NOT CHANGE THIS SECTION ###
    plot_mse()
