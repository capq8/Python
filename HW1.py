#! /usr/bin/python3



def cryptDict(str1, str2):
	#write your code here
	d = {}
	for i,c in enumerate(str1):
		d[c] = str2[i]
	return d
	pass

def decrypt(cdict,s):
	#write your code here
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
	d = {}
	for i,c in enumerate(s):
		if (d.has_key(c) == False):
			d[c] = s.count(c)
	return sorted(d.items(), key=lambda s: (s[1], s[0]))
	


print(charCount("Cpts355 --- Assign1"))




#print(decrypt(cryptDict("abc", "xyz"), "adz"))
