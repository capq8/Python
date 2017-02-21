Halting/Computability:  
turning & church - as powerful as turing machine & lambda calc, we cant solve every partial function   
computable function != total function  
computable function != partial function   
partial f = 1-1  
total f = 1-many | 1-1  

Postscript:  
dup - duplicate the top value on the stack  
• pop - pop the top value from the stack  
• = - pop the top value from the stack and print it  
• stack - display the contents of the stack  
• count - push a count of how many values are on the stack  
• exch - exchange the top two stack values  
• 4 index - copy the 4th stack value (from top) onto the top  
(staring from index 0)  
• 2 copy - copy the top 2 stack values onto the stack  
• count copy - copy the entire stack on itself!  
• 4 2 roll - move the top 2 values on the stack into  
% the 4th stack position from the top  
• 4 -2 roll - move the last 2 of the top 4 values to  
% the top of the stack
• count -1 roll - move the bottom stack value to the top  


dict - operator takes the initial size of the dictionary from the
stack, and puts a brand new empty dictionary on the operand
stack.
begin - operator takes a dictionary from the top of the
operand stack and pushes it on the dictionary stack.
end - operator - pop the top dictionary from the dictionary
stack and throw it away. 

put/get sample:
/mydict 10 dict def
mydict /x 123 put
mydict /x get
pstack // 123 top most stack

<array> <index> <value> put
– puts <value> at <index> of <array>
<array> <index> get
– Gets the object at <index> of <array>
PS declare function: 
/toInch {12 mul} def

PS call function:
2 toInch

PS if: 
/x 3 def
x 3 eq
{x 1 add}
if

ifelse:
/x 4 def
x 3 eq
{x 1 add /x exch def}
{x 4 eq
{x 2 add /x exch def}
{x 3 add /x exch def}
ifelse }
ifelse

PS Loops:
<init> <incr> <final> <code array> for 
ex: 1 1 3 {10 mul} for 

PS Array:
GS> /myArray [0 1 2] def %define an array variable
GS> myArray
GS<1> myArray
GS<2> pstack
[0 1 2]
[0 1 2]
GS<2> myArray 1 (one) put %put (one) at index 1
GS<2> pstack
[0 (one) 2]
[0 (one) 2]
GS<2> 2 get %get element at index 2
GS<2> pstack
2
[0 (one) 2]
GS<2>


[1 2 3 4] {1 add} forall
[1 2 3 4] length




PS Length : [1 2 3 4] length

Postscript defines a forall operator that takes an
array and a code-block as operands. The code-block is
performed on each member of the array.
– Example: [1 2 3 4] {1 add} forall


LISP:
Many major good ideas originated in dialects of
Lisp:
– garbage collection,
– strong typing,
– lists as a pervasive data structure,
– heavy use of higher-order, anonymous, and
recursive functions,
– dynamic typing.

scheme basic pre fix operator 
(+ 2 (+ 3 5) 7) -> (+ 2 8 7) -> 17

define values:
 (define x 3) 
 (define x (+ 2 1))

retreive value:
(+ x 1) 

Conditionals (if)
(if test expr1 expr2)

Special rule for if:
• Evaluate test.
• If test is true evaluate expr1,
• otherwise evaluate expr 2. 



Functions 
(define (avg x y) (/ (+ x y) 2))

Calling Function:
(avg 6 2)

Calling anonymous functions:
((lambda (x y) (/ (+ x y) 2)) 2 6)


 Difference between let and let*:
• In let expression, the initial values are computed before any of the variables
become bound.
• In a let* expression, the evaluations and bindings are sequentially interleaved


LET example:
( let ( (x (* 1 2))
 (y (* 3 4)))
 (+ x y))


scheme comparison:
(= 3 3) -> #t
(eqv? “3” “3”) -> #t
(equal? ‘(1 2 3) (list 1 2 3)) -> #t
(null? ‘()) -> #t
(number? 2) -> #t
(pair? ‘(1 2)) -> #t

scheme functions:

Length List:
(define (mylength L) (cond
 ((null? L) 0)
 (else (+ 1 (mylength (cdr L))) ) )
)


Addup List:
(define (addup L) (cond
 ((null? L) 0)
 (else (+ (car L) (addup (cdr L))) ) )
)

Multiply all:
(define (mulall L) (cond
 ((null? L) 1)
 (else (* (car L) (mulall(cdr L))) ) )
)
(mulall‘(1 2 3 4))
(mulall'()) 

Double all:
(define (doubleAll L) (cond
 ((null? L) '())
 (else (cons (* 2 (car L)) (doubleAll(cdr L))) ) )
)



