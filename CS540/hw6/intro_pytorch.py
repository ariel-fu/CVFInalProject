import numpy as np
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from torchvision import datasets, transforms

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_data_loader(training = True):
    """
    TODO: implement this function.

    INPUT: 
        An optional boolean argument (default value is True for training dataset)

    RETURNS:
        Dataloader for the training set (if training = True) or the test set (if training = False)
    """
    custom_transform= transforms.Compose([ transforms.ToTensor(), transforms.Normalize((0.1307,), (0.3081,))])
    data_set = None
    if(not(training)):
        data_set = datasets.MNIST('./data', train=False, transform=custom_transform)
    else:
        data_set = datasets.MNIST('./data', train=True, download=True, transform=custom_transform)

    loader = torch.utils.data.DataLoader(data_set, batch_size = 50)

    return loader


def build_model():
    """
    TODO: implement this function.

    INPUT: 
        None

    RETURNS:
        An untrained neural network model
    """
    model = nn.Sequential(
       nn.Flatten(),
       nn.Linear(784, 128),
       nn.ReLU(),
       nn.Linear(128, 64),
       nn.ReLU(),
       nn.Linear(64, 10),
    )

    return model




def train_model(model, train_loader, criterion, T):
    """
    TODO: implement this function.

    INPUT: 
        model - the model produced by the previous function
        train_loader  - the train DataLoader produced by the first function
        criterion   - cross-entropy 
        T - number of epochs for training

    RETURNS:
        None
    """
    optimizer = optim.SGD(model.parameters(), lr=0.001, momentum=0.9)
    trainingPrintout = "Train Epoch: {}        Accuracy: {:.0f}/{} ({:.2f}%)       Loss: {:.3f}"
    # we know that the dataset has 60,000 data points
    totalSamples = 60000

    for epoch in range(T):
        trainingLoss = 0
        trainingAcc = 0
        batchDatapoints = 0
        # set model to training and pick SGD as optimization alg
        model.train()

        for data in train_loader:
            inputs, labels = data
            # zero the curr gradients
            optimizer.zero_grad()

            # calculate the prediction
            predictions = model(inputs)
            # calculate the loss
            loss = criterion(predictions, labels)

            # move in the backward direction
            loss.backward()
            optimizer.step()

            # accumulate the loss
            trainingLoss += loss.item() * labels.size()[0]
            # add to the number of data points in this batch
            batchDatapoints += labels.size()[0]
            # get the predicted labels
            _, predictedLabels = torch.max(predictions.data, 1)
            # calculate and add training accuracy
            trainingAcc += (predictedLabels == labels).sum().item()
            # for label, prediction in zip (labels, predictedLabels):
            #     if(label == prediction):
            #         trainingAcc += 1

        print(trainingPrintout.format(epoch, trainingAcc, totalSamples, 100*(trainingAcc/batchDatapoints), trainingLoss/batchDatapoints))

            
    


def evaluate_model(model, test_loader, criterion, show_loss = True):
    """
    TODO: implement this function.

    INPUT: 
        model - the the trained model produced by the previous function
        test_loader    - the test DataLoader
        criterion   - cropy-entropy 

    RETURNS:
        None
    """
    # set model to evaluate and pick SGD as optimization alg
    model.eval()
    
    trainingLoss = 0
    trainingAcc = 0
    totalDatapoints = 60000
    with torch.no_grad():
        batchDatapoints = 0
        for data in test_loader:
            inputs, labels = data

            # calculate the prediction
            predictions = model(inputs)
            # calculate the loss
            loss = criterion(predictions, labels)

            # accumulate the loss
            trainingLoss += loss.item()
            # add to the number of data points in this batch
            batchDatapoints += labels.size()[0]
            # get the predicted labels
            _, predictedLabels = torch.max(predictions.data, 1)
            # calculate and add training accuracy
            for label, prediction in zip (labels, predictedLabels):
                if(label == prediction):
                    trainingAcc += 1
    if(show_loss):
        trainingLoss = 100*(trainingLoss/totalDatapoints)
        print("Average loss: ", trainingLoss)

    trainingAcc = 100*(trainingAcc/batchDatapoints)
    print("Accuracy: ", trainingAcc, "%")



def predict_label(model, test_images, index):
    """
    TODO: implement this function.

    INPUT: 
        model - the trained model
        test_images   -  test image set of shape Nx1x28x28
        index   -  specific index  i of the image to be tested: 0 <= i <= N - 1


    RETURNS:
        None
    """



if __name__ == '__main__':
    '''
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    '''

    train_loader = get_data_loader()
    print(type(train_loader))

    print(train_loader.dataset)

    test_loader = get_data_loader(False)

    model = build_model()

    print(model)

    criterion = nn.CrossEntropyLoss()

    train_model(model, train_loader, criterion, T = 5)

    evaluate_model(model, test_loader, criterion, show_loss = False)

    evaluate_model(model, test_loader, criterion, show_loss = True)
