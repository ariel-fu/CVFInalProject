# -*- coding: utf-8 -*-
"""
Created on Wed Sep  8 22:44:11 2021

@author: Ariel
"""

print("hello wolrd")

x = 0
while x < 10:
    x+=1
    print("x:  ",  x, "\n")
    
square = 3
squares = [square*2 for square in range(5)]
print(squares)

with open("C:/users/Ariel/desktop/test.txt", 'r') as f:
    for line in f:
        print(line)