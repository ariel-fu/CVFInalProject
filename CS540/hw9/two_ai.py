import random
import copy
import time

# trial depth_value
DEPTH_VALUE = 3

class Teeko2Player:
    """ An object representation for an AI game player for the game Teeko2.
    """
    board = [[' ' for j in range(5)] for i in range(5)]
    pieces = ['b', 'r']
    # moves that can be made (row, column)
    RIGHT = (0, 1)
    LEFT = (0, -1)
    UP = (-1, 0)
    DOWN = (1, 0) 
    RIGHT_UP = (-1, 1)
    RIGHT_DOWN = (1, 1)
    LEFT_UP = (-1, -1)
    LEFT_DOWN = (1, -1)

    def __init__(self):
        """ Initializes a Teeko2Player object by randomly selecting red or black as its
        piece color.
        """
        # TEST
        # self.my_piece = 'b'
        self.my_piece = random.choice(self.pieces)
        self.opp = self.pieces[0] if self.my_piece == self.pieces[1] else self.pieces[1]

    def make_move(self, state):
        """ Selects a (row, col) space for the next move. You may assume that whenever
        this function is called, it is this player's turn to move.

        Args:
            state (list of lists): should be the current state of the game as saved in
                this Teeko2Player object. Note that this is NOT assumed to be a copy of
                the game state and should NOT be modified within this method (use
                place_piece() instead). Any modifications (e.g. to generate successors)
                should be done on a deep copy of the state.

                In the "drop phase", the state will contain less than 8 elements which
                are not ' ' (a single space character).

        Return:
            move (list): a list of move tuples such that its format is
                    [(row, col), (source_row, source_col)]
                where the (row, col) tuple is the location to place a piece and the
                optional (source_row, source_col) tuple contains the location of the
                piece the AI plans to relocate (for moves after the drop phase). In
                the drop phase, this list should contain ONLY THE FIRST tuple.

        Note that without drop phase behavior, the AI will just keep placing new markers
            and will eventually take over the board. This is not a valid strategy and
            will earn you no points.
        """

        drop_phase = self.detect_drop_state(state, self.my_piece)   # TODO: detect drop phase (finished)

        # select an unoccupied space randomly
        # TODO: implement a minimax algorithm to play better
        curr_minimax_value, _, next_state = self.minimax_value(state=state, depth=0)

        move = []

        start, end = self.compare_states(state, next_state)

        if not drop_phase:
            # TODO: choose a piece to move and remove it from the board
            # (You may move this condition anywhere, just be sure to handle it)
            #
            # Until this part is implemented and the move list is updated
            # accordingly, the AI will not follow the rules after the drop phase!        
            move.append(start)
        
        # ensure the destination (row,col) tuple is at the beginning of the move list
        move.insert(0, end)
        return move

    def opponent_move(self, move):
        """ Validates the opponent's next move against the internal board representation.
        You don't need to touch this code.

        Args:
            move (list): a list of move tuples such that its format is
                    [(row, col), (source_row, source_col)]
                where the (row, col) tuple is the location to place a piece and the
                optional (source_row, source_col) tuple contains the location of the
                piece the AI plans to relocate (for moves after the drop phase). In
                the drop phase, this list should contain ONLY THE FIRST tuple.
        """
        # validate input
        if len(move) > 1:
            source_row = move[1][0]
            source_col = move[1][1]
            if source_row != None and self.board[source_row][source_col] != self.opp:
                self.print_board()
                print(move)
                raise Exception("You don't have a piece there!")
            if abs(source_row - move[0][0]) > 1 or abs(source_col - move[0][1]) > 1:
                self.print_board()
                print(move)
                raise Exception('Illegal move: Can only move to an adjacent space')
        if self.board[move[0][0]][move[0][1]] != ' ':
            raise Exception("Illegal move detected")
        # make move
        self.place_piece(move, self.opp)

    def place_piece(self, move, piece):
        """ Modifies the board representation using the specified move and piece

        Args:
            move (list): a list of move tuples such that its format is
                    [(row, col), (source_row, source_col)]
                where the (row, col) tuple is the location to place a piece and the
                optional (source_row, source_col) tuple contains the location of the
                piece the AI plans to relocate (for moves after the drop phase). In
                the drop phase, this list should contain ONLY THE FIRST tuple.

                This argument is assumed to have been validated before this method
                is called.
            piece (str): the piece ('b' or 'r') to place on the board
        """
        if len(move) > 1:
            self.board[move[1][0]][move[1][1]] = ' '
        self.board[move[0][0]][move[0][1]] = piece

    def print_board(self):
        """ Formatted printing for the board """
        for row in range(len(self.board)):
            line = str(row)+": "
            for cell in self.board[row]:
                line += cell + " "
            print(line)
        print("   A B C D E")

    def game_value(self, state):
        """ Checks the current board status for a win condition

        Args:
        state (list of lists): either the current state of the game as saved in
            this Teeko2Player object, or a generated successor state.

        Returns:
            int: 1 if this Teeko2Player wins, -1 if the opponent wins, 0 if no winner

        TODO: complete checks for diagonal and 3x3 square corners wins
        """
        
        # check horizontal wins
        for row in state:
            for i in range(2):
                if row[i] != ' ' and row[i] == row[i+1] == row[i+2] == row[i+3]:
                    return 1 if row[i]==self.my_piece else -1

        # check vertical wins
        for col in range(5):
            for i in range(2):
                if state[i][col] != ' ' and state[i][col] == state[i+1][col] == state[i+2][col] == state[i+3][col]:
                    return 1 if state[i][col]==self.my_piece else -1

        # TODO: check \ diagonal wins (finished)
        win, piece = self.checkLeftDiagonal(state)
        if(win):
            return 1 if piece==self.my_piece else -1        
        # TODO: check / diagonal wins (finished)
        win, piece = self.checkRightDiagonal(state)
        if(win):
            return 1 if piece==self.my_piece else -1        
        # TODO: check 3x3 square corners wins (finished)
        win, piece = self.checkSquare(state)
        if(win):
            return 1 if piece==self.my_piece else -1        

        return 0 # no winner yet

############################################################################
#
# HELPER METHODS
#
############################################################################  
    
    ## compares two states to find the different coordinate
    ## returns the (start, end) given two states
    ## if it is in drop state, starting_point returns as None
    ## assumes that the states are different
    def compare_states(self, state, other):
        row_index = 0
        col_index = 0
        starting_point = None
        for row in state:
            for col in row:
                if(state[row_index][col_index] != ' ' and other[row_index][col_index] == ' '):
                    # get the starting point
                    starting_point = (row_index, col_index)
                
                if(state[row_index][col_index] == ' ' and other[row_index][col_index] != ' '):
                    # get the moved point
                    ending_point = (row_index, col_index)
                col_index += 1
            row_index += 1
            col_index = 0
        return starting_point, ending_point


    ## state = curr state, d = upper bound on search depth
    # returns the minimax_value, the state with that value, and the parent
    def minimax_value(self, state, depth):
        # if state is a terminal state, return the win value
        if(self.game_value(state) != 0):
            return self.game_value(state), state, None
        # if d = depth_value, return the heuristic game value of the state
        if(depth == DEPTH_VALUE):
            return self.heuristic_game_value(state), state, None
        # else:
        # get the successors
        if(depth%2 == 0):
            successors = self.succ(state, self.my_piece)
        else:
            successors = self.succ(state, self.opp)
        game_values = []
        # for all the successors:
        for succ in successors:
            # find the game value of the state (whether that is the win/lose or the heuristic value):
            curr_state_game_value, succState, _ = self.minimax_value(succ, depth+1)
            # add the value of the successor, the successor, and the parent state
            game_values.append([curr_state_game_value, succState, succ])  

            # TEST
            # print(succ, curr_state_game_value)

        # if it is Max's move, then return the max out of all the game values
        if(depth % 2 == 0):            
            # 
            max_value = max(game_values)
            # TEST
            # print("max: ", max_value)
            return max_value
        # else: return the min out of all the game values
        else:
            return min(game_values)


    ## determines a heuristic value for the state by evaluating every AI marker on the board 
    # returns the final sum normalized
    def heuristic_game_value(self, state):
        total_heuristic_value = 0
        row_index = 0
        col_index = 0 
        for row in state:
            for col in row:
                if(col == self.my_piece):
                    markerIndex = (row_index, col_index)
                    total_heuristic_value += self.weight(state, markerIndex)
                col_index += 1
            row_index += 1
            col_index = 0
        # normalize the value
        return total_heuristic_value/4

    ## calculates the weight of a marker
    def weight(self, state, markerIndex):
        row, col = markerIndex
        marker = state[row][col]
        opponent = self.opp
        # keep track of the connections
        horizontalConnection = 1
        horizontalBlock = 0
        verticalConnection = 1
        verticalBlock = 0
        diagonalConnection = 1
        diagonalBlock = 0
        squareConnection = 1
        squareBlock = 0
        # calculate horizontal connections or blocks
        try:
            if(state[row][col-1] == marker):
                horizontalConnection += 1
            if(state[row][col-2] == marker):
                horizontalConnection += 1
            # if(state[row][col-3] == marker):
            #     horizontalConnection += 1
            
            if(state[row][col-1] == opponent):
                horizontalBlock += 1
            if(state[row][col-2] == opponent):
                horizontalBlock += 1
            if(state[row][col-3] == opponent):
                horizontalBlock += 1
        except:
            pass
        try:
            horizontalConnectionReverse = 1
            horizontalBlockReverse = 0
            # check index = [row][col+1]
            if(state[row][col+1] == marker):
                horizontalConnectionReverse += 1
            if(state[row][col+1] == opponent):
                horizontalBlockReverse += 1
            # check index = [row][col+2]
            if(state[row][col+2] == marker):
                horizontalConnectionReverse += 1       
            if(state[row][col+2] == opponent):
                horizontalBlockReverse += 1

            # check index = [row][col+3]
            # if(state[row][col+3] == marker):
            #     horizontalConnectionReverse += 1
            if(state[row][col+3] == opponent):
                horizontalBlockReverse += 1
        except:
            pass
        finally:
            if(horizontalConnection < horizontalConnectionReverse):
                horizontalConnection = horizontalConnectionReverse
            if(horizontalBlock < horizontalBlockReverse):
                horizontalBlock = horizontalBlockReverse
       

        # check tricky case of middle in horizontal moves
        try:
            horizontalConnectionTricky = 1
            horizontalBlockTricky = 0
            # check index = [row][col-1]
            if(state[row][col-1] == marker):
                horizontalConnectionTricky += 1    
            if(state[row][col-1] == opponent):
                horizontalBlockTricky += 1
            # NOTE: if this did not happen, then it did not fill a middle case
            # check index = [row][col+1]
            if(state[row][col+1] == marker):
                horizontalConnectionTricky += 1       
            if(state[row][col+1] == opponent):
                horizontalBlockTricky += 1            
        except:
            # check index = [row][col+2]
            pass            
        finally:
            # see if it blocked a 3 case to the right
            try:
                if(state[row][col+2] == opponent):
                    horizontalBlockTricky += 1
            except:
                pass
            # see if it blocked a 3 case to the left
            try:                
                if(state[row][col-2] == opponent):
                    horizontalBlockTricky += 1 
            except:
                pass

            if(horizontalConnection < horizontalConnectionTricky):
                horizontalConnection = horizontalConnectionTricky
            if(horizontalBlock < horizontalBlockTricky):
                ## TEST
                # print("success - block")
                horizontalBlock = horizontalBlockTricky


        # calculate vertical connections
        try:
            if(state[row-1][col] == marker):
                verticalConnection += 1
            if(state[row-2][col] == marker):
                verticalConnection += 1         
            # if(state[row-3][col] == marker):
            #     verticalConnection += 1   
            
            if(state[row-1][col] == opponent):
                verticalBlock += 1
            if(state[row-2][col] == opponent):
                verticalBlock += 1         
            if(state[row-3][col] == opponent):
                verticalBlock += 1         
        except:
            pass     
        try:
            verticalConnectionReverse = 1
            verticalBlockReverse = 0

            if(state[row+1][col] == marker):
                verticalConnectionReverse += 1
            if(state[row+2][col] == marker):
                verticalConnectionReverse += 1           
            # if(state[row-3][col] == marker):
            #     verticalConnectionReverse += 1    

            if(state[row+1][col] == opponent):
                verticalBlockReverse += 1
            if(state[row+2][col] == opponent):
                verticalBlockReverse += 1           
            if(state[row-3][col] == opponent):
                verticalBlockReverse += 1         
        except:
            pass
        finally:
            if(verticalConnection < verticalConnectionReverse):
                verticalConnection = verticalConnectionReverse 

            if(verticalBlock < verticalBlockReverse):
                verticalBlock = verticalBlockReverse 
        

        # check tricky case of middle for the vertical case

        try:
            verticalConnectionTricky = 1
            verticalBlockTricky = 0
            # check index = [row][col-1]
            if(state[row-1][col] == marker):
                verticalConnectionTricky += 1    
            if(state[row-1][col] == opponent):
                verticalBlockTricky += 1
            # NOTE: if this did not happen, then it did not fill a middle case
            # check index = [row][col+1]
            if(state[row+1][col] == marker):
                verticalConnectionTricky += 1       
            if(state[row+1][col] == opponent):
                verticalBlockTricky += 1            
        except:
            # check index = [row][col+2]
            pass            
        finally:
            # see if it blocked a 3 case to the top
            try:
                if(state[row-2][col] == opponent):
                    verticalBlockTricky += 1
            except:
                pass
            # see if it blocked a 3 case to the bottom
            try:                
                if(state[row+2][col] == opponent):
                    verticalBlockTricky += 1 
            except:
                pass

            if(horizontalConnection < verticalConnectionTricky):
                verticalConnection = verticalConnectionTricky
            if(horizontalBlock < verticalBlockTricky):
                verticalBlock = verticalBlockTricky




        # calculate left diagonal connections
        try:
            if(state[row-1][col-1] == marker):
                diagonalConnection += 1
            if(state[row-2][col-2] == marker):
                diagonalConnection += 1
            # if(state[row-3][col-3] == marker):
            #     diagonalConnection += 1

            if(state[row-1][col-1] == opponent):
                diagonalBlock += 1
            if(state[row-2][col-2] == opponent):
                diagonalBlock += 1
            if(state[row-3][col-3] == opponent):
                diagonalBlock += 1
        except:
            pass
        try:
            leftDiagonalConnectionReverse = 1
            leftDiagonalBlockReverse = 0

            if(state[row+1][col+1] == marker):
                leftDiagonalConnectionReverse += 1
            if(state[row+2][col+2] == marker):
                leftDiagonalConnectionReverse += 1
            # if(state[row+3][col+3] == marker):
            #     leftDiagonalConnectionReverse += 1

            if(state[row+1][col+1] == opponent):
                leftDiagonalBlockReverse += 1
            if(state[row+2][col+2] == opponent):
                leftDiagonalBlockReverse += 1
            if(state[row+3][col+3] == opponent):
                leftDiagonalBlockReverse += 1               
        except:
            pass
        finally:
            if(diagonalConnection < leftDiagonalConnectionReverse):
                diagonalConnection == leftDiagonalConnectionReverse
            if(diagonalBlock < leftDiagonalBlockReverse):
                diagonalBlock == leftDiagonalBlockReverse


        # TODO: check tricky case of middle in the case of a left diagonal

        try:
            leftDiagonalConnectionTricky = 1
            leftDiagonalBlockTricky = 0
            # check index = [row][col-1]
            if(state[row-1][col-1] == marker):
                leftDiagonalConnectionTricky += 1    
            if(state[row-1][col-1] == opponent):
                leftDiagonalBlockTricky += 1
            # NOTE: if this did not happen, then it did not fill a middle case
            # check index = [row][col+1]
            if(state[row+1][col+1] == marker):
                leftDiagonalConnectionTricky += 1       
            if(state[row+1][col+1] == opponent):
                leftDiagonalBlockTricky += 1            
        except:
            # check index = [row][col+2]
            pass            
        finally:
            # see if it blocked a 3 case to the top
            try:
                if(state[row-2][col-2] == opponent):
                    leftDiagonalBlockTricky += 1
            except:
                pass
            # see if it blocked a 3 case to the bottom
            try:                
                if(state[row+2][col+2] == opponent):
                    leftDiagonalBlockTricky += 1 
            except:
                pass

            if(horizontalConnection < leftDiagonalConnectionTricky):
                diagonalConnection = leftDiagonalConnectionTricky
            if(horizontalBlock < leftDiagonalBlockTricky):
                diagonalBlock = leftDiagonalBlockTricky


        # calculate right diagonal connections
        try:
            rightDiagonalConnection = 1
            rightDiagonalBlock = 0

            if(state[row-1][col+1] == marker):
                rightDiagonalConnection += 1
            if(state[row-2][col+2] == marker):
                rightDiagonalConnection += 1
            # if(state[row-3][col+3] == marker):
            #     rightDiagonalConnection += 1
            
            if(state[row-1][col+1] == opponent):
                rightDiagonalBlock += 1
            if(state[row-2][col+2] == opponent):
                rightDiagonalBlock += 1
            if(state[row-3][col+3] == opponent):
                rightDiagonalBlock += 1
        except:
            pass
        finally:
            if(diagonalConnection < rightDiagonalConnection):
                diagonalConnection == rightDiagonalConnection
            if(diagonalBlock < rightDiagonalBlock):
                diagonalBlock == rightDiagonalBlock

        try:
            rightDiagonalConnectionReverse = 1
            rightDiagonalBlockReverse = 0

            if(state[row+1][col-1] == marker):
                rightDiagonalConnectionReverse += 1
            if(state[row+2][col-2] == marker):
                rightDiagonalConnectionReverse += 1
            # if(state[row+3][col-3] == marker):
            #     rightDiagonalConnectionReverse += 1

            if(state[row+1][col-1] == opponent):
                rightDiagonalBlockReverse += 1
            if(state[row+2][col-2] == opponent):
                rightDiagonalBlockReverse += 1
            if(state[row+3][col-3] == opponent):
                rightDiagonalBlockReverse += 1
            
        except:
            pass
        finally:
            if(diagonalConnection < rightDiagonalConnectionReverse):
                diagonalConnection = rightDiagonalConnectionReverse
    
            if(diagonalBlock < rightDiagonalBlockReverse):
                diagonalBlock = rightDiagonalBlockReverse

        # TODO: check tricky case of middle in the case of a right diagonal
        try:
            rightDiagonalConnectionTricky = 1
            rightDiagonalBlockTricky = 0
            # check index = [row+1][col-1]
            if(state[row+1][col-1] == marker):
                rightDiagonalConnectionTricky += 1    
            if(state[row+1][col-1] == opponent):
                rightDiagonalBlockTricky += 1
            # NOTE: if this did not happen, then it did not fill a middle case
            # check index = [row-1][col+1]
            if(state[row-1][col+1] == marker):
                rightDiagonalConnectionTricky += 1       
            if(state[row-1][col+1] == opponent):
                rightDiagonalBlockTricky += 1            
        except:
            pass            
        finally:
            # see if it blocked a 3 case to the bottom
            try:
                if(state[row+2][col-2] == opponent):
                    rightDiagonalBlockTricky += 1
            except:
                pass
            # see if it blocked a 3 case to the top
            try:                
                if(state[row-2][col+2] == opponent):
                    rightDiagonalBlockTricky += 1 
            except:
                pass

            if(horizontalConnection < rightDiagonalConnectionTricky):
                diagonalConnection = rightDiagonalConnectionTricky
            if(horizontalBlock < rightDiagonalBlockTricky):
                diagonalBlock = rightDiagonalBlockTricky


        # calculate square connections
        try:    
            if(state[row+2][col] == marker):
                squareConnection += 1
            if(state[row][col+2] == marker):
                squareConnection += 1
            if(state[row+2][col+2] == marker):
                squareConnection += 1 

            if(state[row+2][col] == opponent):
                squareBlock += 1
            if(state[row][col+2] == opponent):
                squareBlock += 1
            if(state[row+2][col+2] == opponent):
                squareBlock += 1            
        except:
            pass

        try:
            squareConnectionUpperRight = 1
            squareBlockUpperRight = 0

            if(state[row-2][col] == marker):
                squareConnectionUpperRight += 1
            if(state[row][col+2] == marker):
                squareConnectionUpperRight += 1
            if(state[row-2][col+2] == marker):
                squareConnectionUpperRight += 1    

            if(state[row-2][col] == opponent):
                squareBlockUpperRight += 1
            if(state[row][col+2] == opponent):
                squareBlockUpperRight += 1
            if(state[row-2][col+2] == opponent):
                squareBlockUpperRight += 1            
        except:
            pass
        finally:
            if(squareConnection < squareConnectionUpperRight):
                squareConnection = squareConnectionUpperRight
            
            if(squareBlock < squareBlockUpperRight):
                squareBlock = squareBlockUpperRight
        
        try:
            squareConnectionUpperLeft = 1
            squareBlockUpperLeft = 0

            if(state[row-2][col] == marker):
                squareConnectionUpperLeft += 1
            if(state[row][col-2] == marker):
                squareConnectionUpperLeft += 1
            if(state[row-2][col-2] == marker):
                squareConnectionUpperLeft += 1 

            if(state[row-2][col] == opponent):
                squareBlockUpperLeft += 1
            if(state[row][col-2] == opponent):
                squareBlockUpperLeft += 1
            if(state[row-2][col-2] == opponent):
                squareBlockUpperLeft += 1 
        except:
            pass
        finally:
            if(squareConnection < squareConnectionUpperLeft):
                squareConnection = squareConnectionUpperLeft

            if(squareBlock < squareBlockUpperLeft):
                squareBlock = squareBlockUpperLeft

        try:
            squareConnectionLowerLeft = 1
            squareBlockLowerLeft = 0

            if(state[row+2][col] == marker):
                squareConnectionLowerLeft += 1
            if(state[row][col-2] == marker):
                squareConnectionLowerLeft += 1
            if(state[row+2][col-2] == marker):
                squareConnectionLowerLeft += 1  

            if(state[row-2][col] == opponent):
                squareBlockLowerLeft += 1
            if(state[row][col+2] == opponent):
                squareBlockLowerLeft += 1
            if(state[row-2][col+2] == opponent):
                squareBlockLowerLeft += 1  
        except:
            pass
        finally:
            if(squareConnection < squareConnectionLowerLeft):
                squareConnection = squareConnectionLowerLeft

            if(squareBlock < squareBlockLowerLeft):
                squareBlock = squareBlockLowerLeft
        
        if(horizontalBlock == 3 or verticalBlock == 3 or diagonalBlock == 3 or squareBlock == 3):
            return 0.75
        if(horizontalConnection == 3 or verticalConnection == 3 or diagonalConnection == 3 or squareConnection == 3):
            return 0.5
        if(horizontalConnection == 2 or verticalConnection == 2 or diagonalConnection == 2 or squareConnection == 2):
            return 0.25
        if(horizontalConnection == 1 or verticalConnection == 1 or diagonalConnection == 1 or squareConnection == 1):
            return 0
        if(horizontalBlock == 2 or verticalBlock == 2 or diagonalBlock == 2 or squareBlock == 2):
            return -0.25
        if(horizontalBlock == 1 or verticalBlock == 1 or diagonalBlock == 1 or squareBlock == 1):
            return -.5
        if(horizontalBlock == 0 or verticalBlock == 0 or diagonalBlock == 0 or squareBlock == 0):
            return -0.75
        if(horizontalConnection == 0 or verticalConnection == 0 or diagonalConnection == 0 or squareConnection == 0):
            return -0.75
            
    def detect_drop_state(self, state, piece):
        numMarkers = 0
        row_index = 0
        col_index = 0
        for row in state:
            for col in row:
                if(state[row_index][col_index] == piece):
                    numMarkers+=1
                col_index += 1
            row_index += 1
            col_index = 0

        if(numMarkers < 4):
            return True
        else:
            return False

    def succ(self, state, piece):
        # keep track of the curr marker's index in the array
        col = 0
        row = 0
        # keep track of the next successors
        successors = []
        # drop phase
        if(self.detect_drop_state(state, piece)):
            dropCoordinates = self.drop_coordinates(state)
            return self.drop_piece(state, dropCoordinates, piece)
        
        # only go into shifting after the drop phase is finished
        origState = state[:]
        for stateRow in state:
            for value in stateRow:
                # if this value is a spot aka not ' ' and it is my piece:
                if(not(value == ' ') and value == piece):
                    # get the tuple (x, y)
                    markersIndex = (row, col)
                    # make the move      
                    moves = self.shiftPiece(origState, markersIndex)    
                    # add possible moves to the list of next moves
                    successors+=(moves)
                # increment the col
                col += 1
            # increment the row
            row += 1
            # set the col back to 0
            col = 0

        return successors
 
    ## simulates dropping a piece on the board
    ## returns all possible coordinates where there is an open space
    def drop_coordinates(self, state):
        newCoordinates = []
        row_index = 0
        col_index = 0
        for row in state:
            for col in row:
                if(state[row_index][col_index] == ' '):
                    newCoordinates += [(row_index, col_index)]
                col_index += 1
            row_index += 1
            col_index = 0
        return newCoordinates
    
    ## gets all possible board states for a drop
    ## returns all possible board states for a drop given 
    def drop_piece(self, state, indices, piece):
        origState = copy.deepcopy(state)
        successors = []
        for index in indices:
            row, col = index
            origState[row][col] = piece
            successors += [origState]
            origState = copy.deepcopy(state)
        return successors
            

        
    
    ## simulates shifting a single marker
    ## returns all possible coordinates a marker can be shifted to
    def shiftPiece(self, state, markersIndex):
        row, col = markersIndex
        listOfSuccessors = []
        newCoordinates = []
        if(row == 0):
            if(col == 0):
                # get the set of moves of what moves can be made
                # make right, down, rightdown moves
                possibleMoves = [self.RIGHT, self.DOWN, self.RIGHT_DOWN]                
            elif(col == 4):                
                # get the set of moves of what moves can be made
                # make left, down, leftdown moves
                possibleMoves = [self.LEFT, self.DOWN, self.LEFT_DOWN]
            else:
                # get the set of moves of what moves can be made
                # make left, right, down, leftdown, rightdown
                possibleMoves = [self.LEFT, self.RIGHT, self.DOWN, self.LEFT_DOWN, self.RIGHT_DOWN]
        elif(row == 4):
            if(col == 0):
                # get the set of moves of what moves can be made
                # make right, up, rightup moves
                possibleMoves = [self.RIGHT, self.UP, self.RIGHT_UP]
            elif(col == 4):
                # get the set of moves of what moves can be made
                # make left, up, leftup moves
                possibleMoves = [self.LEFT, self.UP, self.LEFT_UP]
            else:
                # get the set of moves of what moves can be made
                # make left, right, up, leftup, rightup moves
                possibleMoves = [self.LEFT, self.RIGHT, self.UP, self.LEFT_UP, self.RIGHT_UP]
        elif(col == 0):
            # get the set of moves of what moves can be made
            # make right, up, down, rightup, rightdown moves
            possibleMoves = [self.RIGHT, self.UP, self.DOWN, self.RIGHT_UP, self.RIGHT_DOWN]
        elif(col == 4):
            # get the set of moves of what moves can be made
            # make left, up, down, leftup, leftdown moves
            possibleMoves = [self.LEFT, self.UP, self.DOWN, self.LEFT_UP, self.LEFT_DOWN]
        else:
            # get the set of moves of what moves can be made
            # make left, right, up, down, leftup, leftdown, rightup rightdown moves
            possibleMoves = [self.LEFT, self.RIGHT, self.UP, self.DOWN, self.LEFT_UP, self.LEFT_DOWN, self.RIGHT_UP, self.RIGHT_DOWN]
        # for each move validate whether the move is OK or not
        for move in possibleMoves:
            if(self.validate(state, markersIndex, move)):
                # if the move is valid: get the new coordinates and add to an array 
                nextMove = (row+move[0], col+move[1])
                newCoordinates.append(nextMove)
                # print("curr moves: ", newCoordinates)
        
        origState = copy.deepcopy(state)
        for coordinate in newCoordinates:
            listOfSuccessors += [self.operate(origState, (row, col), coordinate)]
            origState = copy.deepcopy(state)
        
        return listOfSuccessors
    
    ## Input: self, state (state of the board), row (row of the marker), col (col of the marker), direction (a direction of movement)
    ## returns true if the spot in the direction is empty
    def validate(self, state, currSpot, direction):
        row, col = currSpot
        row_increment, col_increment = direction
        return state[row + row_increment][col + col_increment] == ' '    

    def operate(self, state, currPos, newPos):
        currRow, currCol = currPos
        newRow, newCol = newPos
        state[newRow][newCol] = state[currRow][currCol]
        state[currRow][currCol] = ' '
        return state

    ## given a state: check for success in a 3x3 square
    ## returns True and the value of the success 
    def checkSquare(self, state):
        for row in range(3):
            for col in range(3):
                if(state[row][col] != ' ' and state[row][col] == state[row+2][col] == state[row][col+2] == state[row+2][col+2]):
                    return True, state[row][col]
        return False, ' '
    ## given a state: check for success in a left-diagonal fashion
    ## returns True and the value of the success
    def checkLeftDiagonal(self, state):
        if(state[1][0] != ' ' and state[1][0] == state[2][1] == state[3][2] == state[4][3]):
            return True, state[1][0]
        elif(state[0][1] != ' ' and state[0][1] == state[1][2] == state[2][3] == state[3][4]):
            return True, state[0][1]
        elif(state[0][0] != ' ' and state[0][0] == state[1][1] == state[2][2] == state[3][3]):
            return True, state[0][0]
        elif(state[1][1] != ' ' and state[1][1] == state[2][2] == state[3][3] == state[4][4]):
            return True, state[1][1]
        else:
            return False, ' '
    ## given a state: check for success in a right-diagonal fashion
    ## returns True and the value of the success
    def checkRightDiagonal(self, state):
        if(state[3][0] != ' ' and state[3][0] == state[2][1] == state[1][2] == state[0][3]):
            return True, state[3][0]
        elif(state[4][1] != ' ' and state[4][1] == state[3][2] == state[2][3] == state[1][4]):
            return True, state[4][1]
        elif(state[4][0] != ' ' and state[4][0] == state[3][1] == state[2][2] == state[1][3]):
            return True, state[4][0]
        elif(state[3][1] != ' ' and state[3][1] == state[2][2] == state[1][3] == state[0][4]):
            return True, state[3][1]
        else:
            return False, ' '












############################################################################
#
# TESTING
#
############################################################################
 
# def test():
#     test = Teeko2Player()


#     state = [['b', ' ', ' ', ' ', ' '], 
#              [' ', ' ', ' ', ' ', ' '], 
#              [' ', ' ', ' ', ' ', ' '], 
#              [' ', ' ', ' ', ' ', ' '], 
#              [' ', ' ', ' ', ' ', 'r']]

#     nextmove = test.make_move(state)
#     print(nextmove)

#     move = test.make_move(state)
#     print(move)

    # other = [['r', ' ', ' ', ' ', 'r'], 
    #          ['b', 'r', ' ', ' ', ' '], 
    #          [' ', 'b', ' ', ' ', ' '], 
    #          [' ', ' ', ' ', ' ', ' '], 
    #          [' ', 'b', ' ', 'b', ' ']]
    # start, end = test.compare_states(state, other)
    # print(start, end)

    # minimaxValue, newstate, parent = test.minimax_value(state=state, depth=0)
    # print(*newstate, sep = "\n")
    # print(" =============== ")
    # print(*parent, sep = "\n")
    # print(minimaxValue)















    # succ = test.succ(state=state, piece='r')
    # for s in succ:
    #     print(*s, sep = "\n")
    #     print(" ---- ")
    # successors = test.succ(state=state)
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("-----------------")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    #          [' ', ' ', ' ', 'r', ' '], 
    #          [' ', ' ', 'b', ' ', ' '], 
    #          [' ', 'r', ' ', ' ', ' '], 
    #          ['r', ' ', ' ', ' ', ' ']]
    # index = (2, 2)
    # tricky = test.weight(state, index)
    # print(tricky)

    # state = [[' ', ' ', ' ', ' ', ' '], 
    #          [' ', 'r', ' ', ' ', ' '], 
    #          [' ', ' ', 'b', ' ', ' '], 
    #          [' ', ' ', ' ', 'r', ' '], 
    #          [' ', ' ', ' ', ' ', 'r']]
    # index = (2, 2)
    # tricky = test.weight(state, index)
    # print(tricky)
    # # minimax_value
    # state = [['r', ' ', ' ', ' ', 'r'], 
    #          [' ', 'r', ' ', ' ', ' '], 
    #          [' ', 'b', ' ', 'b', ' '], 
    #          [' ', ' ', ' ', ' ', ' '], 
    #          [' ', 'b', 'b', ' ', ' ']]
    # time_start = time.time()
    # finishedstate, minimaxvalue = test.minimax_value(state=state, depth = 0)
    # time_end = time.time()
    # print(*finishedstate, sep='\n')
    # print("time: ", time_end-time_start)

    # state = [[' ', 'b', 'r', ' ', ' '], 
    # ['r', ' ', ' ', ' ', ' '], 
    # ['r', ' ', ' ', ' ', ' '], 
    # ['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # time_start = time.time()
    # version1 = test.weight(state=state, markerIndex=(3, 0))
    # time_end = time.time()
    # print("version 1: ", version1, " time: ", time_end-time_start)

    # state = [['r', 'b', 'r', ' ', ' '], 
    # ['r', ' ', ' ', ' ', ' '], 
    # ['r', ' ', ' ', ' ', ' '], 
    # ['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # time_start = time.time()
    # version1 = test.weight(state=state, markerIndex=(3, 0))
    # time_end = time.time()
    # print("version 1: ", version1, " time: ", time_end-time_start)

    # state = [['b', 'b', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # time_start = time.time()
    # version1 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 1: ", time_end-time_start)
    
    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # time_start = time.time()
    # version2 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 2: ", time_end - time_start)

    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', 'b', ' ']]
    # time_start = time.time()
    # version2 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 3: ", time_end - time_start)
    
    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # ['b', 'b', ' ', 'b', ' ']]
    # time_start = time.time()
    # version2 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 4: ", time_end - time_start)

    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # ['b', ' ', ' ', 'b', ' '], 
    # ['b', ' ', ' ', 'b', ' '], 
    # ['b', 'b', ' ', 'b', ' ']]
    # time_start = time.time()
    # version2 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 5: ", time_end - time_start)

    # state = [['b', 'b', 'b', ' ', 'b'], 
    # [' ', 'b', ' ', ' ', ' '], 
    # ['b', ' ', ' ', 'b', ' '], 
    # ['b', ' ', 'b', 'b', ' '], 
    # ['b', 'b', ' ', 'b', ' ']]
    # time_start = time.time()
    # version2 = test.heuristic_game_value(state)
    # time_end = time.time()
    # print("version 6: ", time_end - time_start)
        
    # print(version1)
    # print(version2)
    # print(version1 < version2)




    # state = [['b', 'b', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # version1 = test.heuristic_game_value(state)
    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # version2 = test.heuristic_game_value(state)
    # print(version1)
    # print(version2)
    # print(version1 < version2)

    # state = [[' ', 'b', ' ', ' ', 'b'], 
    # [' ', ' ', 'b', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # ['b', 'b', 'b', ' ', ' ']]
    # time_start = time.time()
    # weight = test.weight(state, (4, 0))
    # time_end = time.time()
    # print("time: ", time_end-time_start, " weight: ", weight)    
    # print(test.weight(state, (0, 4)))

    # diagonal from below
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', '', ' ', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', ' ', 'b']]
    
    # print(test.weight(state, (4, 4)))

    # up
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', 'b'], 
    # ['b', ' ', 'b', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (1, 4)))


    # state = [[' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', 'b'], 
    # ['b', ' ', 'b', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (1, 4)))


    # state = [[' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', 'b'], 
    # ['b', ' ', 'b', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (2, 4)))

    # lower right square
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (0, 0)))

    # upper right square
    # state = [[' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (2, 0)))

    # lower left square
    # state = [[' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (0, 2)))

    # upper left square
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (2, 2)))

    # right downward diagonal
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(test.weight(state, (2, 1)))


    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    
    # print(0.5 == test.weight(state, (2, 2)))
    
    # down
    # state = [['b', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # print(0.5 == test.weight(state, (0, 0)))
    
    # right
    # state = [['b', 'b', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]

    # print(0.5 == test.weight(state, (0, 0)))
    

    # state = [['b', ' ', 'b', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # ['b', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', 'a', 'a'], 
    # [' ', ' ', ' ', 'a', 'a']]

    # truth, value = test.checkSquare(state)
    # print("truth: {}, value: {}".format(truth, value))
    # truth, value = test.checkRightDiagonal(state)
    # print("truth: {}, value: {}".format(truth, value))
    
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' ']]

    # truth, value = test.checkRightDiagonal(state)
    # print("truth: {}, value: {}".format(truth, value))
    
    # state = [['b', ' ', ' ', ' ', ' '], 
    # ['b', 'b', ' ', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' ']]

    # truth, value = test.checkLeftDiagonal(state)
    # print("truth: {}, value: {}".format(truth, value))

    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', ' ', 'b']]

    # truth, value = test.checkLeftDiagonal(state)
    # print("truth: {}, value: {}".format(truth, value))

    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', 'b', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', 'b', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]

    # truth, value = test.checkLeftDiagonal(state)
    # print("truth: {}, value: {}".format(truth, value))

    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]

    # print(*test.right(state, 0, 0), sep='\n')
    # print("-------------------------")
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]

    # print(*test.down(state, 0, 0), sep='\n')
    # print("-------------------------")
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # print(*test.rightdown(state, 0, 0), sep='\n')
    
    # print("-------------------------")
    # state = [[' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # print(*test.left(state, 0, 4), sep='\n')

    # print("-------------------------")
    # state = [[' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # print(*test.leftdown(state, 0, 4), sep='\n')

    # print("-------------------------")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' ']]
    # print(*test.up(state, 4, 0), sep='\n')

    # print("-------------------------")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' ']]
    # print(*test.upRight(state, 4, 0), sep='\n')
    
    # print("-------------------------")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', 'b']]
    # print(*test.upLeft(state, 4, 4), sep='\n')
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (0, 0))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")

    # state = [[' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (0, 4))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")

    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (4, 0))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', 'b']]
    # successors = test.move(state, (4, 4))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # ['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (2, 0))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' ']]
    # successors = test.move(state, (4, 2))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', 'b'], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (2, 4))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    # state = [[' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', 'b', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.move(state, (2, 2))
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.succ(state)
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")
    
    # state = [['b', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', 'b']]
    # successors = test.succ(state)
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")

    # state = [['b', 'b', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.succ(state)
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")

    # state = [['b', 'a', ' ', ' ', ' '], 
    # [' ', ' ', ' ', 'c', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' '], 
    # [' ', ' ', ' ', ' ', ' ']]
    # successors = test.succ(state)
    # for succ in successors:
    #     print(*succ, sep = "\n")
    #     print("=============")

    

############################################################################
#
# THE FOLLOWING CODE IS FOR SAMPLE GAMEPLAY ONLY
#
############################################################################
def main():
    print('Hello, this is Samaritan')
    ai = Teeko2Player()
    aiOpp = Teeko2Player()
    piece_count = 0
    turn = 0

    # drop phase
    while piece_count < 8 and ai.game_value(ai.board) == 0:

        # get the player or AI's move
        if ai.my_piece == ai.pieces[turn]:
            ai.print_board()
            # TEST
            time_start = time.time()
            move = ai.make_move(ai.board)

            # TEST
            time_end = time.time()
            # TEST
            print("____________________")
            print("time: ", time_end-time_start)
            print("____________________")

            ai.place_piece(move, ai.my_piece)

            print(ai.my_piece+" moved at "+chr(move[0][1]+ord("A"))+str(move[0][0]))
        else:
            aiOpp.print_board()
            # TEST
            time_start = time.time()
            move = aiOpp.make_move(aiOpp.board)

            # TEST
            time_end = time.time()
            # TEST
            print("____________________")
            print("time: ", time_end-time_start)
            print("____________________")

            aiOpp.place_piece(move, aiOpp.my_piece)

            print(aiOpp.my_piece+" moved at "+chr(move[0][1]+ord("A"))+str(move[0][0]))

        # update the game variables
        piece_count += 1
        turn += 1
        turn %= 2

    # move phase - can't have a winner until all 8 pieces are on the board
    while ai.game_value(ai.board) == 0:

        # get the player or AI's move
        if ai.my_piece == ai.pieces[turn]:
            ai.print_board()
            
            # TEST
            time_start = time.time()
            move = ai.make_move(ai.board)

            # TEST
            time_end = time.time()
            # TEST
            print("____________________")
            print("time: ", time_end-time_start)
            print("____________________")

            ai.place_piece(move, ai.my_piece)
            print(ai.my_piece+" moved from "+chr(move[1][1]+ord("A"))+str(move[1][0]))
            print("  to "+chr(move[0][1]+ord("A"))+str(move[0][0]))


        else:
            aiOpp.print_board()
            
            # TEST
            time_start = time.time()
            move = aiOpp.make_move(aiOpp.board)

            # TEST
            time_end = time.time()
            # TEST
            print("____________________")
            print("time: ", time_end-time_start)
            print("____________________")

            aiOpp.place_piece(move, aiOpp.my_piece)
            print(aiOpp.my_piece+" moved from "+chr(move[1][1]+ord("A"))+str(move[1][0]))
            print("  to "+chr(move[0][1]+ord("A"))+str(move[0][0]))

        # update the game variables
        turn += 1
        turn %= 2

    ai.print_board()
    if ai.game_value(ai.board) == 1:
        print("AI wins! Game over.")
    else:
        print("You win! Game over.")


if __name__ == "__main__":
    # test()

    ## ARIEL: TEST / DEBUG - BRING MAIN BACK

    main()