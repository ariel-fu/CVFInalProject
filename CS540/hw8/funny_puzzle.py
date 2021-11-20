import numpy as np
import heapq as heap
import time

SUCCESS = [1, 2, 3, 4, 5, 6, 7, 8, 0]

def print_succ(state):
    output = "{} h={}"
    outputList = get_succ(state)
    for outputValue in outputList:
        succState, h = outputValue
        print(output.format(succState, h))

## ADD CREDITS
def solve(state):
    if(state == SUCCESS):
        return "??????"
    # create OPEN heap
    open = []
    # create CLOSE heap
    close = []
    # create parent_list = []
    parent_list = []
    # create parent_index = 0
    parent_index = 0
    # keep track of the number of moves made
    g = 0
    # get the first node
    h = calcManhattanState(state)
    originalState = create_state(g+h, g, h, state, parent_index-1)
    currValue = originalState
    # add to the open heap
    heap.heappush(open, currValue)
    # stringify state
    currStateString = ''.join([str(n) for n in currValue[1]])
    # add to the list of visited states
    visitedStates = {currStateString}

    ## TEST
    iteration = 0
    # while open heap is not empty:
    while(len(open) != 0):
        start = time.time()
        # pop from open
        currValue = heap.heappop(open)
        # push to close
        heap.heappush(close, currValue)
        # parent_list[parent_index] = currNode
        parent_list.insert(parent_index, currValue)
        # stringify state
        currStateString = ''.join([str(n) for n in currValue[1]])
        # add to the list of visited states
        visitedStates.add(currStateString)
        # get the curr state
        currState = currValue[1]
        # if curr is success: 
        if(currState == SUCCESS):
            # declare success
            # begin back tracking
            backtrack(originalState, parent_list, currValue)
            return
        # else:
        # add 1 to the number of moves made (g)
        g += 1
        # generate list of successors, along with h
        successors = get_succ(currState)
        # parent_index ++ 
        parent_index += 1
        # for each successor:
        for successor in successors:
            # successor, h
            succ, h = successor
            # f = g + h
            f = g + h
            # parent_index = parent_index-1
            p_index = parent_index-1
            # create new node and add these values
            currSuccessorNode = create_state(g+h, g, h, succ, p_index)
            addCurr = True
            # stringify the successor
            currStateString = ''.join([str(n) for n in currSuccessorNode[1]])
            # if successor is already in the heap, check if the f value is smaller
            # if successor is already in the heap, do not add it
            if(currStateString in visitedStates):
                # if the f value is smaller: remove duplicate
                # if the f value is larger: do not add succ
                # addCurr, close = removeDuplicate(close, currSuccessorNode)
                addCurr = False
            # else, add the successor to OPEN
            if(addCurr):
                heap.heappush(open, currSuccessorNode)
        
        
        # print(*visitedStates, sep='\n')
        end = time.time()
        if(iteration%100 == 0):
            # print("=-=-=-=-===-=-=--==-=-=--==-=-=-=-=-=-===-==---=-=-=-==-")
            print(end-start)
            print("iteration: ", iteration)
        iteration+=1
        # print("open: ")
        # print(*open, sep='\n')
        # print("=-=-=-=-===-=-=--==-=-=--==-=-=-=-=-=-===-==---=-=-=-==-")
        # print("close: ")
        # print(*close, sep='\n')
    print("failed")


####################### HELPER FUNCTIONS #######################
## TODO: given a parent_list, print out the steps
def backtrack(originalState, parent_list, currValue):
    # get the parent index
    parent_index = currValue[2][2]
    # keep track of the path list:
    pathList = [currValue]
    # moves made
    
    while(parent_index != 0):
        parent = parent_list[parent_index]
        pathList.insert(0, parent)
        parent_index = parent[2][2]
    
    # add the starting stage
    pathList.insert(0, originalState)
    outputFormat = "{} h={} moves: {}"
    movesMade = 0
    for state in pathList:
        h = state[2][1]
        stateValue = state[1]
        print(outputFormat.format(stateValue, h, movesMade))
        movesMade += 1

## create a state
def create_state(f, g, h, state, parent_index):
    return (f, state, (g, h, parent_index))        

## check if the successor is already in the heap
# def isInHeap(open, node):
#     nodeState = node[1]
#     for value in open:
#         state = value[1]
#         if(state == nodeState):
#             return True
#     return False

## given a heap and a duplicate value, remove the duplicate value
# def removeDuplicate(close, duplicate):
#     duplicateF = duplicate[0]
#     duplicateState = duplicate[1]
#     index = 0
#     addCurr = True
#     for element in close:
#         currF = element[0]
#         currState = element[1]
#         if(currState == duplicateState):
#             if(currF > duplicateF):
#                 close.pop(index)
#             else:
#                 addCurr = False
#         index += 1

#     return addCurr, close

    

## get the successors and their respective 'h' values in a list
def get_succ(state):
    successors = getNextStates(state)
    output = "{} h={}"
    outputList = []
    isFirst = True
    for succState in successors:
        h = calcManhattanState(succState)
        if(isFirst):
            outputList = [(succState, h)]
            isFirst = False
        else:
            outputList = outputList + [(succState, h)]
    outputList = sorted(outputList)
    return outputList

## calculate the Manhattan for a whole state
## state = state in question
def calcManhattanState(state):
    totManhattan = 0
    index = 0    
    for value in state:
        totManhattan += calcManhattanInd(index, value)
        index += 1
    return totManhattan


## calculate the Manhattan of a tile
## index = index of value, value = state[index]
def calcManhattanInd(index, value):
    # DO NOT calculate manhattan of '0' value
    if(value == 0):
        return 0
    # expected index of value = (value-1)
    expectedIndex = value-1
    # see if it is already in the correct place
    if(index == expectedIndex):
        return 0
    # get expected coordinates
    expectedCoord = getCoordinates(expectedIndex)
    # get current coordinates
    currentCoord = getCoordinates(index)
    # calculate manhattan
    xDif = abs(expectedCoord[0] - currentCoord[0])
    yDif = abs(expectedCoord[1] - currentCoord[1])
    manhattan = xDif + yDif
    # return result
    return manhattan


## get the (x, y) coordinates of an index
def getCoordinates(index):
    if(index == 0):
        return (0, 0)
    elif(index == 1):
        return (1, 0)
    elif(index == 2):
        return (2, 0)
    elif(index == 3):
        return (0, 1)
    elif(index == 4):
        return (1, 1)
    elif(index == 5):
        return (2, 1)
    elif(index == 6):
        return (0, 2)
    elif(index == 7):
        return (1, 2)
    else:
        return (2, 2)

## takes in the current state and calculates the succ states
## returns an array containing all possible succ states
def getNextStates(state):
    index = 0
    for value in state:
        if(value == 0):
            break
        index += 1
    # keep track of the indices with swap values
    listOfSwapIndices = []
    if(index == 0):    
        # right operation: swap [0] with [1]
        # top operation: swap [0] with [3]
        listOfSwapIndices = [1, 3]
    elif(index == 1):
        # left operation: swap [1] with [0]
        # right operation: swap [1] with [2]
        # bottom operation: swap [1] with [4]
        listOfSwapIndices = [0, 2, 4]
    elif(index == 2):
        # left operation: swap [2] with [1]
        # bottom operation: swap [2] with [5]
        listOfSwapIndices = [1, 5]
    elif(index == 3):
        # up operation: swap [3] with [0]
        # right operation: swap [3] with [4]
        # bottom operation: swap [3] with [6]
        listOfSwapIndices = [0, 4, 6]
    elif(index == 4):
        # top operation: swap [4] with [1]
        # left operation: swap [4] with [3]
        # right operation: swap [4] with [5]
        # bottom operation: swap [4] with [7]
        listOfSwapIndices = [1, 3, 5, 7]
    elif(index == 5):
        # top operation: swap [5] with [2]
        # left operation: swap [5] with [4]
        # bottom operation: swap [5] with [8]
        listOfSwapIndices = [2, 4, 8]
    elif(index == 6):
        # top operation: swap [6] with [3]
        # right operation: swap [6] with [7]
        listOfSwapIndices = [3, 7]
    elif(index == 7):
        # top operation: swap [7] with [4]
        # left operation: swap [7] with [6]
        # right operation: swap [7] with [8]
        listOfSwapIndices = [4, 6, 8]
    else:
        # top operation: swap [8] with [5]
        # left operation: swap [8] with [7]
        listOfSwapIndices = [5, 7]
    
    # hold the next states in an array
    listOfStates = []
    isFirst = True
    for swapIndex in listOfSwapIndices:
        # get the original state to modify
        origState = state[:]
        if(isFirst):
            listOfStates = [operate(origState, index, swapIndex)]
            isFirst = False
        else:
            listOfStates = listOfStates + [operate(origState, index, swapIndex)]
    
    # return the list of successor states
    return listOfStates
        
        
## swap the value at [zeroIndex] with the value at [swapIndex]
## swap the value at [swapIndex] with the value 0
## return the new state after performing an "operation"
def operate(state, zeroIndex, swapIndex):
    state[zeroIndex] = state[swapIndex]
    state[swapIndex] = 0
    return state

#### TEST
#### DELETE
def main():
    # state = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    # getNextStates(state)
    # state = [1, 0, 2, 3, 4, 5, 6, 7, 8]
    # getNextStates(state)
    # state = [1, 2, 3, 4, 0, 5, 6, 7, 8]
    # getNextStates(state)

    # print(calcManhattanInd(7, 1))
    # print(calcManhattanInd(0, 2))
    # print(calcManhattanInd(1, 5))
    # print(calcManhattanInd(2, 8))

    # state = [2, 5, 8, 4, 3, 6, 7, 1, 0]
    # print(calcManhattanState(state))

    # state = [1, 2, 3, 4, 5, 0, 6, 7, 8]
    # print_succ(state)

    # state = [5, 7, 0, 8, 1, 2, 4, 3, 6]
    # print_succ(state)

    # create a heap out of puzzleState(f, g, h, state, parent_index)
    # value1 = create_state(10, 5, 5, [1, 2, 3], -1)
    # duplicate = create_state(12, 7, 5, [1, 2, 3], -1)
    # value2 = create_state(10, 4, 6, [9, 2, 3], -1)
    # open = []
    # heap.heappush(open, value1)
    # heap.heappush(open, value2)
    # heap.heappush(open, duplicate)
    # print(removeDuplicate(open, duplicate))

    parent_list = []
    value1 = create_state(10, 5, 5, [1, 2, 3], -1)
    duplicate = create_state(12, 7, 5, [1, 2, 3], 0)
    value2 = create_state(10, 9, 6, [9, 2, 3], 1)
    
    # heap.heappush(parent_list, value1)
    # heap.heappush(parent_list, duplicate)
    # heap.heappush(parent_list, value2)

    # backtrack(parent_list, value2)


    # state = [1, 2, 3, 4, 5, 6, 7, 0, 8]
    # solve(state)
    # solve([1, 2, 3, 4, 5, 6, 0, 7, 8])
    # solve([1, 2, 3, 4, 0, 5, 7, 8, 6])
    # solve([0, 1, 3, 4, 2, 5, 7, 8, 6])
    # solve([4,3,8,5,1,6,7,2,0])
    solve([1, 2, 3, 0, 4, 6, 7, 5, 8])
    print("nothing")

if __name__ == '__main__':
    main()