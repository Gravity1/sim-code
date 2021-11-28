#   Author: Raphael Migwi 
#   S/w: simple dice simulator
# 
#  face probabilities are randomised and mapped to dye face


# README
# # # # # # # # 
# this program simulates the rolling of dice
# does the computation 1000 times
# instead of randomising the face of the dice that lands
# we are going to get a random float between 0 and 1
# divide up this range into 6 parts
# and map each face to 1 part in range 0-1 
# we are doing this because this is a disrete operation/simulation by nature
# 
# 
# This is a rudimentary program that uses very basic data structures 
# Arrays could be used to shorten this code dramaticall
# a loop to work the array would reduce the number of declarations
# 
# 

import random
import decimal

print(""" 
▓█████▄  ██▓ ▄████▄  ▓█████      ██████  ██▓ ███▄ ▄███▓ █    ██  ██▓    ▄▄▄     ▄▄▄█████▓ ▒█████   ██▀███  
▒██▀ ██▌▓██▒▒██▀ ▀█  ▓█   ▀    ▒██    ▒ ▓██▒▓██▒▀█▀ ██▒ ██  ▓██▒▓██▒   ▒████▄   ▓  ██▒ ▓▒▒██▒  ██▒▓██ ▒ ██▒
░██   █▌▒██▒▒▓█    ▄ ▒███      ░ ▓██▄   ▒██▒▓██    ▓██░▓██  ▒██░▒██░   ▒██  ▀█▄ ▒ ▓██░ ▒░▒██░  ██▒▓██ ░▄█ ▒
░▓█▄   ▌░██░▒▓▓▄ ▄██▒▒▓█  ▄      ▒   ██▒░██░▒██    ▒██ ▓▓█  ░██░▒██░   ░██▄▄▄▄██░ ▓██▓ ░ ▒██   ██░▒██▀▀█▄  
░▒████▓ ░██░▒ ▓███▀ ░░▒████▒   ▒██████▒▒░██░▒██▒   ░██▒▒▒█████▓ ░██████▒▓█   ▓██▒ ▒██▒ ░ ░ ████▓▒░░██▓ ▒██▒
 ▒▒▓  ▒ ░▓  ░ ░▒ ▒  ░░░ ▒░ ░   ▒ ▒▓▒ ▒ ░░▓  ░ ▒░   ░  ░░▒▓▒ ▒ ▒ ░ ▒░▓  ░▒▒   ▓▒█░ ▒ ░░   ░ ▒░▒░▒░ ░ ▒▓ ░▒▓░
 ░ ▒  ▒  ▒ ░  ░  ▒    ░ ░  ░   ░ ░▒  ░ ░ ▒ ░░  ░      ░░░▒░ ░ ░ ░ ░ ▒  ░ ▒   ▒▒ ░   ░      ░ ▒ ▒░   ░▒ ░ ▒░
 ░ ░  ░  ▒ ░░           ░      ░  ░  ░   ▒ ░░      ░    ░░░ ░ ░   ░ ░    ░   ▒    ░      ░ ░ ░ ▒    ░░   ░ 
   ░     ░  ░ ░         ░  ░         ░   ░         ░      ░         ░  ░     ░  ░            ░ ░     ░     
 ░          ░                                                                                              

 .........
:~, *   * ~,
: ~, *   * ~.
:  ~........~
: *:         :      ~'~,
:  :         :    ~' *  ~,
~* :    *    : ,~' *    * ~,
 ~,:         :.~,*    *  ,~ :
  ~:.........::  ~, *  ,~   :
              : *  ~,,~  *  :
              :* * * :  *   :
               ~, *  : *  ,~
                 ~,  :  ,~
                   ~,:,~
""")


faceOne = faceTwo = faceThree = faceFour = faceFive = faceSix = 0
faceOneAverage = faceTwoAverage = faceThreeAverage = faceFourAverage = faceFiveAverage = faceSixAverage = 0
faceOnePercentage = faceTwoPercentage = faceThreePercentage = faceFourPercentage = faceFivePercentage = faceSixPercentage = 0

for x in range(1000):

    foo=float(decimal.Decimal(random.randrange(0, 100))/100)

    if foo >= 0 and foo <= (1 / 6) :
        faceOne = faceOne + 1
    if foo >(1 / 6) and foo <= (2 / 6) :
        faceTwo = faceTwo + 1
    if foo > (2 / 6) and foo <= (3 / 6) :
        faceThree = faceThree + 1
    if foo > (3 / 6) and foo <= (4 / 6) :
        faceFour = faceFour + 1
    if foo > (4 / 6) and foo <= (5 / 6) :
        faceFive = faceFive + 1
    if foo > (5 / 6) and foo <= (6 / 6) :
        faceSix = faceSix + 1

faceOneAverage = faceOne / 1000
faceOnePercentage = faceOneAverage * 100

faceTwoAverage = faceTwo / 1000
faceTwoPercentage = faceTwoAverage * 100

faceThreeAverage = faceThree / 1000
faceThreePercentage = faceThreeAverage * 100

faceFourAverage = faceFour / 1000
faceFourPercentage = faceFourAverage * 100

faceOneAverage = faceOne / 1000
faceOnePercentage = faceOneAverage * 100

faceFiveAverage = faceFive / 1000
faceFivePercentage = faceFiveAverage * 100

faceSixAverage = faceSix / 1000
faceSixPercentage = faceSixAverage * 100

print("""
-----
|   |
| o |
|   |
-----""", faceOne, "average occurence is ", faceOneAverage, "at ", faceOnePercentage," percent" )


print("""
-----
|o  |
|   |
|  o|
-----""", faceTwo, "average occurence is ", faceTwoAverage, "at ", faceTwoPercentage," percent"  )

print("""
-----
|o  |
| o |
|  o|
-----""", faceThree, "average occurence is ", faceThreeAverage, "at ", faceThreePercentage," percent"  )

print("""
-----
|o o|
|   |
|o o|
-----""", faceFour, "average occurence is ", faceFourAverage, "at ", faceFourPercentage," percent"  )

print("""
-----
|o o|
| o |
|o o|
-----""", faceFive, "average occurence is ", faceFiveAverage, "at ", faceFivePercentage," percent"  )

print("""
-----
|o o|
|o o|
|o o|
-----""", faceSix, "average occurence is ", faceSixAverage, "at ", faceSixPercentage," percent"  )

