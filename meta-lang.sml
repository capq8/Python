fun exists(x,[]) = false | 
exists(x,hd::L) = if x = hd then true 
else exists(x,L);

fun listUnion (aL,bL) = let
  	fun build(result, [], []) = result |
	build(result, hd::remain, y) = 
		if exists(hd, result) then build(result, remain, y)
		else build(hd::result, remain, y) |
	build(result, x, hd::remain) = 
		if exists (hd, result) then build(result, x, remain)  
		else build(hd::result, x, remain) 
 	in 
	build([], aL, bL)
end;


fun listIntersect aL bL = let
  	fun build(result, []) = result | build(result, hd::remain) = 
		if exists (hd, bL) then 
			if exists (hd, result) then build(result, remain)
			else build(hd::result, remain)
		else build(result, remain)
 	in build([], aL)
end;

fun pairNleft N L =
	let 
	fun org i [] [] = [[]] |
	org i [] (hd::result) = hd::result |
	org i (tp::remain) (hd::result) = 
		if i > 0 
			then org (i-1) remain ((tp :: hd) :: result)
		else org N (tp::remain) ([] :: hd :: result)
	in 
	if N > 0 then 
	org N L [[]]
	else [[]]

end; 