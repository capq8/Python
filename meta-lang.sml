fun exist (x,y) = 
if tl(x) = []  then false
else
	if hd(x) = hd(y) then true
	else 
		if tl(y) = [] then exist(tl(x), y)
		else exist (x, tl(y));
