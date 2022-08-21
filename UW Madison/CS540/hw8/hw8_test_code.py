import heapq as heap
import time
## TEST DELETE
from itertools import permutations

SUCCESS = [1, 2, 3, 4, 5, 6, 7, 8, 0]

def print_succ(state):
    output = "{} h={}"
    outputList = get_succ(state)
    for outputValue in outputList:
        succState, h = outputValue
        print(output.format(succState, h))

''' Original author: Professor Liang
    Source: http://pages.cs.wisc.edu/~yliang/cs540_1_fall21/documents/CS540-informed-search.pdf (Links to an external site.)
    The pseudocode on page 28 was consistently referenced while coding
'''
''' Original author: Ziyi Zhang
    Source: https://piazza.com/class/ktak19t6klt6rj?cid=584 & https://piazza.com/class/ktak19t6klt6rj?cid=574 (Links to an external site.)
    Discovered the time to find the path is too long - reorganized code based on feedback from Ziyi Zhang.
'''
''' Original author: Python Documentation for heapq
    Source: https://docs.python.org/3/library/heapq.html (Taken from Canvas write-up) (Links to an external site.)
    The implementation details and function documentations were referenced during coding to understand how 'heappush' and 'heappop' work, in addition to seeing if there are other functions that may be useful
'''
def solve(state):
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

    # while open heap is not empty:
    while(len(open) != 0):
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
            currSuccessorNode = create_state(f, g, h, succ, p_index)
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
    # will not come to this case because all cases are assumed to be solvable.
    print("failed")


####################### HELPER FUNCTIONS #######################
## given a parent_list, print out the steps
def backtrack(originalState, parent_list, currValue):
    # get the parent index
    parent_index = currValue[2][2]
    # keep track of the path list:
    pathList = [currValue]    
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

## get the successors and their respective 'h' values in a list
def get_succ(state):
    successors = getNextStates(state)
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


def toString(List):
    return ''.join(List)
 

def permute(s, answer, list_permutation):
    if (len(s) == 0):
        list_permutation += [answer]
        # print(list_permutation)
        return list_permutation
     
    for i in range(len(s)):
        ch = s[i]
        left_substr = s[0:i]
        right_substr = s[i + 1:]
        rest = left_substr + right_substr
        permute(rest, answer + ch, list_permutation)

def getInvCount(arr):
    inv_count = 0
    empty_value = -1
    for i in range(0, 9):
        for j in range(i + 1, 9):
            if arr[j] != empty_value and arr[i] != empty_value and arr[i] > arr[j]:
                inv_count += 1
    return inv_count
 
     
# This function returns true
# if given 8 puzzle is solvable.
def isSolvable(puzzle) :
 
    # Count inversions in given 8 puzzle
    inv_count = getInvCount([j for sub in puzzle for j in sub])
 
    # return true if inversion count is even.
    return (inv_count % 2 == 0)

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
    string = "012345678"
    
    permute_list = [''.join(p) for p in permutations('012345678')]
    # sort them out
    solvablePuzzles=[]
    for permute_item in permute_list:
        puzzle = [[permute_item[0], permute_item[1], permute_item[2]], [permute_item[3], permute_item[4], permute_item[5]], [permute_item[6], permute_item[7], permute_item[8]]]
        if(isSolvable(puzzle)):
            puzzle = sum(puzzle, [])
            puzzle = [int(x) for x in puzzle]
            solvablePuzzles.append(puzzle)

    for puzzle in solvablePuzzles:
        timeStart = time.time()
        solve(puzzle)
        timeEnd = time.time()
        print("      ")
        print("time: ")
        print(timeEnd - timeStart)
        print("-----------")


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