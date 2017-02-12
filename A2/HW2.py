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

def removesqare(str):
  a = list(str)
  a.pop(0)
  a.pop(len(a)- 1)
  a = "".join(a)
  return a


def length():
  a = opPop()
  a = removesqare(a)
  print((a.split()))
  return (len(a.split()))


testAdd()
testSub()
testMul()
testDiv()
testMod()

test = "[1 2 3 [a b]]"
opstack.append(test)
print(length())
