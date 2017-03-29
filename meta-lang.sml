fun exists(x,[]) = false | 
exists(x,hd::L) = if x = hd then true 
else exists(x,L);
