fun exists(x,[]) = false | 
exists(x,hd::L) = if x = hd then true 
else exists(x,L);


fun listIntersect aL bL = let
  	fun build(result, []) = result | build(result, hd::remain) = 
		if exists (hd, bL) then 
			if exists (hd, result) then build(result, remain)
			else build(hd::result, remain)
		else build(result, remain)
 	in build([], aL)
end;

