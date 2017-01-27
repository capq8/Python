#! /usr/bin/python3



def cryptDict(str1, str2):
	#map string by index
	d = {}
	for i,c in enumerate(str1):
		d[c] = str2[i]
	return d
	pass

def decrypt(cdict,s):
	#replace by key value if key exist 
	for i,c in enumerate(s):
		if (cdict.has_key(c)):
			s = s.replace(c,cdict[c])
	return s
	pass

def testDecrypt():
	cdict = cryptDict("abc", "xyz")
	revcdict = cryptDict("xyz", "abc")
	tests = "Now I know my abc's"
	answer = "Now I know my xyz's"
	if(decrypt(cdict, tests) != answer):
		return False
	if (decrypt(revcdict, decrypt(cdict, tests)) != "Now I know mb abc's"):
		return False
	if (decrypt(cdict,'') != ''):
		return False
	if (decrypt(cryptDict('',''), 'abc') != 'abc'):
		return False
	return True

def charCount(s):
	#use dict to count and then use sorted feature to sort
	#freq first, then alph order later. 
	s = s.replace(" ", "")
	d = {}
	for i,c in enumerate(s):
		if (d.has_key(c) == False):
			d[c] = s.count(c)
	return sorted(d.items(), key=lambda s: (s[1], s[0]))
	
def testCount():
	#1. base case, no white space
	#2. test some string
	#3. test some string with special character 
	s1 = dict(charCount(" test "))
	s2 = dict(charCount("hello_test1"))
	s3 = dict(charCount("hello_yhz`';,.>"))
	if(s3['`'] != 1 or s3['_'] != 1):
		return False
	if(s2['l'] != 2):
		return False
	return True

print(testCount())
#print(charCount("Cpts355 --- Assign1"))

d = {Monday:{’355’:2,’451’:1,’360’:2},Tuesday:{’451’:2,’360’:3}, Thursday:{’355’:3,’451’:2,’360’:3}, 
Friday:{’355’:2}, Sunday:{’355’:1,’451’:3,’360’:1}}


#print(decrypt(cryptDict("abc", "xyz"), "adz"))
