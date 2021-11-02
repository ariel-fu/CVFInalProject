import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
import random


def get_dataset(filename):
    """
    TODO: implement this function.

    INPUT: 
        filename - a string representing the path to the csv file.

    RETURNS:
        An n by m+1 array, where n is # data points and m is # features.
        The labels y should be in the first column.
    """
    df = pd.read_csv(filename, index_col=0).reset_index(drop=True)
    dataset = df.to_numpy()
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
    print(len(dataset))
    print("{:.2f}".format(np.mean(dataset, axis=0)[col]))
    print("{:.2f}".format(np.std(dataset, axis=0, ddof=1)[col]))


def get_details(dataset, cols, betas=None):
    if betas is not None:
        betas = np.array(betas)
    y = dataset[:, 0]
    X = dataset[:, cols]
    X = np.hstack((np.ones((len(dataset), 1)), X))
    return X, y, betas


def regression(dataset, cols, betas):
    """
    TODO: implement this function.

    INPUT: 
        dataset - the body fat n by m+1 array
        cols    - a list of feature indices to learn.
                  For example, [1,8] refers to density and abdomen.
        betas   - a list of elements chosen from [beta0, beta1, ..., betam]

    RETURNS:
        mse of the regression model
    """
    X, y, betas = get_details(dataset, cols, betas)
    mse = np.sum((X @ betas - y) ** 2) / len(dataset)
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
    X, y, betas = get_details(dataset, cols, betas)
    grads = 2 / len(dataset) * np.sum((X @ betas - y).reshape((-1, 1)) * X, axis=0)
    return grads


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
    X, y, betas = get_details(dataset, cols, betas)
    for i in range(1, T + 1):
        betas = betas - eta * gradient_descent(dataset, cols, betas)
        mse = regression(dataset, cols, betas)
        print("{} {:.2f}".format(i, mse), end=' ')
        for beta in betas:
            print("{:.2f}".format(beta), end=' ')
        print()


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
    X, y, _ = get_details(dataset, cols)
    betas = np.linalg.inv(X.T @ X) @ (X.T) @ y
    mse = regression(dataset, cols, betas)
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
    ret = compute_betas(dataset, cols)
    betas = np.array(ret[1:])
    features = np.hstack((1, np.array(features)))
    return features @ betas


def synthetic_datasets(betas, alphas, X, sigma):
    np.random.seed(1024)
    X = np.hstack((np.ones((len(X), 1)), X))
    Z = np.random.normal(loc=0, scale=sigma, size=len(X))
    linears = X @ betas + Z
    linears = np.hstack((linears.reshape((-1, 1)), X[:, 1:]))
    qX = np.hstack((X[:, 0].reshape((-1, 1)), (X[:, 1] ** 2).reshape(-1, 1)))
    Z = np.random.normal(loc=0, scale=sigma, size=len(X))
    quads = qX @ alphas + Z
    quads = np.hstack((quads.reshape((-1, 1)), X[:, 1:]))
    return linears, quads


def plot_mse():
    from sys import argv
    if len(argv) == 2 and argv[1] == 'csl':
        import matplotlib
        matplotlib.use('Agg')

    # Your plotting code goes here

    # X = np.linspace(-100,100,1000).reshape((-1, 1))
    X = np.random.uniform(-100, 100, 1000).reshape((-1, 1))
    betas = np.array([1,2])
    alphas = np.array([1, 1])

    mse_ls = []
    mse_qs = []
    print (synthetic_datasets(betas, alphas, X, 10 ** -4)[0][0])
    sigmas = [10 ** n for n in range(-4, 6)]
    for sigma in sigmas:
        linears, quads = synthetic_datasets(betas, alphas, X, sigma)
        mse_ls.append(compute_betas(linears, cols=[1])[0])
        mse_qs.append(compute_betas(quads, cols=[1])[0])
    plt.plot(sigmas, mse_ls, '-o')
    plt.plot(sigmas, mse_qs, '-o')
    plt.legend(["MSE of Linear Dataset", "MSE of Quadratic Dataset"])
    plt.xscale('log')
    plt.yscale('log')
    plt.xlabel("Standard Deviation of Error Term")
    plt.ylabel("MSE of Trained Model")
    plt.savefig("mse_sol.pdf")


if __name__ == '__main__':
    plot_mse()
    dataset = get_dataset('bodyfat.csv')
    # print(iterate_gradient(dataset, cols=[1,8], betas=[400,-400,300], T=10, eta=1e-4))

