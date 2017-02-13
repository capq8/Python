from random import *

opstack = []

def opPop():
  return opstack.pop()

def opPush(value):
  opstack.append(value)

def define(name, value):
  pass

def lookup(name):
  pass


def add():
  a = opPop()
  b = opPop()
  opstack.append(a+b)


def testAdd():
  a = randint(1,100)
  b = randint(1,100)
  opPush(a)
  opPush(b)
  add()

  if(opPop() != (a + b)):
    print("add function failed")
    return False

  print("add function passed.")
  return True


def sub():
  a = opPop()
  b = opPop()
  opstack.append(b-a)

def testSub():
  a = randint(1,100)
  b = randint(1,100)
  opPush(a)
  opPush(b)
  sub()

  if(opPop() != (a - b)):
    print("sub function failed")
    return False

  print("sub function passed.")
  return True


def mul():
    a = opPop()
    b = opPop()
    opstack.append(b*a)

def testMul():
    a = randint(1,100)
    b = randint(1,100)
    opPush(a)
    opPush(b)
    mul()

    if(opPop() != (a * b)):
      print("mul function failed")
      return False

    print("mul function passed.")
    return True



def div():
  a = opPop()
  b = opPop()
  opstack.append(b/a)

def testDiv():
  a = randint(1,100)
  b = randint(1,100)
  opPush(a)
  opPush(b)
  div()

  if(opPop() != (a / b)):
    print("div function failed")
    return False

  print("div function passed.")
  return True


def mod():
  a = opPop()
  b = opPop()
  opstack.append(b%a)

def testMod():
  a = randint(1,100)
  b = randint(1,100)
  opPush(a)
  opPush(b)
  mod()

  if(opPop() != (a % b)):
    print("mod function failed")
    return False

  print("mod function passed.")
  return True

def arrayCounter(a):
  outercount = 0
  innercount = 0
  strCounter = 0

  for i in a:
    if i == '[' and outercount == 0:
      outercount += 1

    elif i == '[' and outercount == 1:
      innercount += 1

    elif i == ']' and outercount == 1 and innercount > 0:
      innercount -= 1

    elif i == ']' and innercount == 0 and outercount > 0:
      outercount -= 1

    elif outercount > 0 and innercount > 0:
      strCounter += 1
      continue 

    elif i == " ":
      s = list(a)
      #add ','' so we can split it into traditional python array 
      #it is much easier to manage array in python
      s[strCounter] = ','
      a = "".join(s)
      
    strCounter += 1    
  a = cleanOuterBracket(a)

  return a.split(',')

def cleanOuterBracket(str):
  a = list(str)
  a.pop(0)
  a.pop(len(a)- 1)
  a = "".join(a)
  return a

def length():
  strArray = arrayCounter(opPop())
  opstack.append(len(strArray))

def testLength():
  opstack.append("[1 2 3 5 [a c d] [] [a b]]")
  length()

  if opPop() != 7:
    print("length function failed.")
    return False

  print("length test passed.")
  return True

def get():
  index = opPop()
  array = arrayCounter(opPop())

  opPush(array[index])

def testGet():
  test = "[1 2 3 5 [a c d] [] [a b]]"
  opstack.append(test)
  opstack.append(1)
  get()
  if opPop() != '2':
    print("get function failed.")
    return False

  print("get test passed.")
  return True





testAdd()
testSub()
testMul()
testDiv()
testMod()
testLength()
testGet()

