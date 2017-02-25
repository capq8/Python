import HW2_partA
import re






def tokenize(s):
	retValue = re.findall("/?[a-zA-Z][a-zA-Z0-9_]*|[[][a-zA-Z0-9_\s!][a-zA-Z0-9_\s!]*[]]|[-]?[0-9]+|[}{]+|%.*|[^ \t\n]", s)
	return retValue


def arrtoken(a):
	inbracket = 0
	temparr = []
	newarr = []
	openposition = []
	closeposition = []
	stoken = tokenize(a)

	# replace item in closed arrays by _ 
	# traverse through later and remove them 
	for i,v in enumerate(stoken):

		if v == '{':
			openposition.append(i)
			inbracket +=1
			temparr = []
			stoken[i] = '_'

		if v == '}':
			closeposition.append(i)
			inbracket -= 1
			newarr.append(temparr)
			stoken[i] = '_'

		if inbracket == 1:
			if v != '{' and v != '}':
				temparr.append(v)
				stoken[i] = '_'


	# append built array to open bracket position
	for pos, item in enumerate(openposition):
		stoken[item] = newarr[pos]

	#then remove leftover key
	while '_' in stoken:
		stoken.remove('_')

	print stoken


s = "/square {dup mul} {1 2 3 4} def 1 square [1 2 3 4] length {2 3 2 4 }"
arrtoken(s)