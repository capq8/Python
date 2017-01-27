#! /usr/bin/python3
#{name: "Quang Nguyen", id: 11399147}
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
	if(s1['t'] != 2 and s1['e'] != 1):
		return False
	return True

def dictAddup(d):
	#1. turn dict to tuple
	#2. interate through tuple and add up all courses key corresponding value
	final = {}
	d = d.items()
	for i,d in d:
		day = d.items()
		for c,h in day:
			if(final.has_key(c)):
				final[c] += h
			else:
				final[c] = h		
	return final
	
def testAddup():
	d = dictAddup({"Monday":{'355':2, '300': 5}, "Tuesday":{'360':5, '355':11}, "Friday":{'360':5, '355':13}})
	d2 = dictAddup({"Monday": {}, "Tuesday":{}, "Friday":{}})
	d3 = dictAddup({"": {}, "":{}, "":{}})

	if(d['355'] != 26):
		return False
	if(d2 != {}):
		return False
	if(d3.items() != []):
		return False

	return True


def testAll():
	if(testAddup() and testCount() and testDecrypt()):
		print("all tests passed successfully.")
		return 1
	else:
		print("test failed.")
		return 0


if __name__ == '__main__':
	testAll()