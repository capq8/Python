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

fun reverse L = 
let
	fun rev [] result = result  |
	rev (hd::remain) result = 
		rev remain (hd::result) 
in
rev L []
end;

fun pairNright N L = 
	if N > 0 then
		reverse (pairNleft N L)
	else [[]];


fun filter pred L = 
let
	fun aux pred [] result = reverse  result |
	aux pred (hd::remain) result = 
		if pred hd 
			then aux pred remain (hd::result) 
		else aux pred remain result 
in 
aux pred L []
end; 


fun mergesort [] = [] |
mergesort [x] = [x] |
mergesort L = let

fun split L = 
let
	fun aux i [] l1 l2= (l1 , l2) |
	aux i (hd::remain) l1 l2 = 
	if i > 0 then aux 0 remain (hd::l1) l2
	else aux 1 remain l1 (hd::l2)
in 
aux 1 L [] []
end

val (L1, L2) = split L

fun merge [] [] = [] |
 merge L1 [] = L1 |
 merge [] L2 = L2 |
 merge (cl1 :: rest1) (cl2 :: rest2) =
if cl1 < cl2
then cl1 :: (merge rest1 (cl2 :: rest2))
else cl2 :: (merge rest2 (cl1 :: rest1))

in
merge (mergesort L1) (mergesort L2)
end

fun mergesort2 [] = [] |
mergesort [x] = [x] |
mergesort L = let

fun split L = 
let
	fun aux i [] l1 l2= (l1 , l2) |
	aux i (hd::remain) l1 l2 = 
	if i > 0 then aux 0 remain (hd::l1) l2
	else aux 1 remain l1 (hd::l2)
in 
aux 1 L [] []
end

val (L1, L2) = split L

fun merge [] [] = [] |
 merge L1 [] = L1 |
 merge [] L2 = L2 |
 merge (cl1 :: rest1) (cl2 :: rest2) =
            if cl1 = cl2
              then 
		(merge rest1 (cl2 :: rest2))
            else if cl1 < cl2 
then cl1 :: (merge rest1 (cl2 :: rest2))
else cl2 :: (merge rest2 (cl1 :: rest1))
  

in
merge (mergesort L1) (mergesort L2)
end
