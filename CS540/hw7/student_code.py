# python imports
import os
from tqdm import tqdm

# torch imports
import torch
import torch.nn as nn
import torch.optim as optim

# helper functions for computer vision
import torchvision
import torchvision.transforms as transforms


class LeNet(nn.Module):
    def __init__(self, input_shape=(32, 32), num_classes=100):
        super(LeNet, self).__init__()
        # certain definitions
        # Conv2d(in, out channels = 6, kernel size = 5, stride = 1, padding = 0)
        self.conv1 = nn.Conv2d(in_channels=3, out_channels=6, kernel_size=5, stride=1, padding=0)
        # MaxPool2d(kernel size = 2, stride =2, padding = 0)
        self.pool1 = nn.MaxPool2d(kernel_size=2, stride=2, padding=0)
        # Conv2d(in = (32-5+1)/2 = 14, out channels = 16, kernel size = 5, stride = 1, padding = 0)
        self.conv2 = nn.Conv2d(in_channels=6, out_channels=16, kernel_size=5, stride=1, padding=0)
        # MaxPool2d(kernel size = 2, stride = 2, padding = 0)
        self.pool2 = nn.MaxPool2d(kernel_size=2, stride=2, padding=0)
        # linear layer with input 16*5*5 and output 256
        self.fc1 = nn.Linear(16*5*5, 256)
        # linear layer with input 256 and output 128
        self.fc2 = nn.Linear(256, 128)
        # linear layer with input 128 and output 100/num_classes
        self.fc3 = nn.Linear(128, num_classes)


    def forward(self, x):
        shape_dict = {}
        # convolve, then perform ReLU
        out = nn.functional.relu(self.conv1(x))
        # apply pooling
        out = self.pool1(out)
        # stage 1 - dimensions = [batch, 6, 14, 14]
        # print("stage 1:")
        shape_dict[1] = list(out.shape)
        # print(shape_dict)
        # print("-------")
        # convolve, then perform ReLU
        out = nn.functional.relu(self.conv2(out))
        # apply pooling
        out = self.pool2(out)
        # stage 2
        # print("stage 2:")
        shape_dict[2] = list(out.shape)
        # print(shape_dict)
        # print("-------")
        # flatten x to contian 16*5*5 columns
        out = out.view(-1, 16*5*5)
        #stage 3
        # print("stage 3:")
        shape_dict[3] = list(out.shape)
        # print(shape_dict)
        # print("-------")

        # run through dense neural network, then perform ReLU
        out = nn.functional.relu(self.fc1(out))
        # stage 4
        # print("stage 4:")
        shape_dict[4] = list(out.shape)
        # print(shape_dict)
        # print("-------")
        
        # run through 2nd dense neural network, then perform ReLU
        out = nn.functional.relu(self.fc2(out))
        # stage 5
        # print("stage 5:")
        shape_dict[5] = list(out.shape)
        # print(shape_dict)
        # print("-------")
        
        # run through last dense neural network
        out = self.fc3(out)
        # stage 6
        # print("stage 6:")
        shape_dict[6] = list(out.shape)
        # print(shape_dict)
        # print("-------")
        #TEST
        count_model_params()

        return out, shape_dict


def count_model_params():
    '''
    return the number of trainable parameters of LeNet.
    '''
    model = LeNet()
    model_params = 0.0
    parameterArray = model.named_parameters()
    index = 0
    for parameters in model.named_parameters():
        name, parameter = parameters
        length = len(parameter.shape)
        parameterSizes = parameter.shape
        currLearnable = 0
        if(length == 4):
            output = parameterSizes[0]
            input = parameterSizes[1]
            kernelHeight = parameterSizes[2]
            kernelWidth = parameterSizes[3]
            currLearnable += (input * kernelHeight * kernelWidth + 1)
            # TEST
            print(currLearnable)
            currLearnable *= output
            # TEST
            print(currLearnable)
        elif(length == 2):
            output = parameterSizes[0]
            input = parameterSizes[1]
            currLearnable += (output * input)
            # TEST
            print(currLearnable)
            currLearnable += output
            # TEST
            print(currLearnable)
        else:
            currLearnable = 0
        model_params += currLearnable
    model_params /= 1e6
    return model_params


def train_model(model, train_loader, optimizer, criterion, epoch):
    """
    model (torch.nn.module): The model created to train
    train_loader (pytorch data loader): Training data loader
    optimizer (optimizer.*): A instance of some sort of optimizer, usually SGD
    criterion (nn.CrossEntropyLoss) : Loss function used to train the network
    epoch (int): Current epoch number
    """
    model.train()
    train_loss = 0.0
    for input, target in tqdm(train_loader, total=len(train_loader)):
        ###################################
        # fill in the standard training loop of forward pass,
        # backward pass, loss computation and optimizer step
        ###################################

        # 1) zero the parameter gradients
        optimizer.zero_grad()
        # 2) forward + backward + optimize
        output, _ = model(input)
        loss = criterion(output, target)
        loss.backward()
        optimizer.step()

        # Update the train_loss variable
        # .item() detaches the node from the computational graph
        # Uncomment the below line after you fill block 1 and 2
        train_loss += loss.item()

    train_loss /= len(train_loader)
    print('[Training set] Epoch: {:d}, Average loss: {:.4f}'.format(epoch+1, train_loss))

    return train_loss


def test_model(model, test_loader, epoch):
    model.eval()
    correct = 0
    with torch.no_grad():
        for input, target in test_loader:
            output, _ = model(input)
            pred = output.max(1, keepdim=True)[1]
            correct += pred.eq(target.view_as(pred)).sum().item()

    test_acc = correct / len(test_loader.dataset)
    print('[Test set] Epoch: {:d}, Accuracy: {:.2f}%\n'.format(
        epoch+1, 100. * test_acc))

    return test_acc
