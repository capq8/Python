(define (addup L) (cond
 ((null? L) 0)
 (else (+ (car L) (addup (cdr L))) ) )
)

(define (deepSum L) (cond
                      ((null? L) 0)
                      ((null? (car L))(+ 0 (deepSum (cdr L))))
                      ((pair? (car L)) (+ (addup (car L)) (deepSum (cdr L))))
                      (not (list? (car L))(+ (car L) (deepSum (cdr L))))
                      (else (+ (car L) (deepSum (cdr L))) )
                      ))

(define (remove-last lst)
    (if (null? (cdr lst))
        '()
        (cons (car lst) (remove-last (cdr lst)))))

(define (numbersToSum sum L) (cond
    ((<= sum (addup L)) (numbersToSum sum (remove-last L)))
    (else L)))

;first violation return #f else #t
(define (isSorted L) 
  (cond ((null? L) #t)
        ((eq? (length L) 1) #t)
        ((< (car L) (car (cdr L))) (isSorted(cdr L)))
        (else #f)))

;compare two value, inserting the smaller one first 
(define (mergeUnique2 L1 L2)        
   (cond ( (null? L1) L2)
         ( (null? L2) L1)
         ( (eq? (car L1)(car L2)) (mergeUnique2 (cdr L1)L2) )
         ( (< (car L1)(car L2))
              (cons (car L1) (mergeUnique2 (cdr L1)L2)))
         (else
              (cons (car L2) (mergeUnique2 L1 (cdr L2))))
))

(define (fold f base L) ( cond
((null? L) base)
(else (f (car L) (fold f base (cdr L))))
))


;standard
(define (mergeUniqueN L) (fold mergeUnique2 '() L 
))


; use map to call f on car item then cons recursively
(define (matrixMap f L ) (cond
      ( (null? L) '())
      (else (cons (map f (car L)) (matrixMap f (cdr L))))
))
                         
(define (oddlistmaker L) (cond
      ((null? L) '())
      ((odd? (car L)) (cons (car L) (oddlistmaker (cdr L))))
      (else (oddlistmaker (cdr L)))
))    
    
; get odd arr, add up, div by length                   
(define (avgOdd L) (cond
                  ((null? (oddlistmaker L)) '())
                  (else (/(fold + 0 (oddlistmaker L)) (length (oddlistmaker L))))
))

;return 2 list on null, map head of list to head of end list                  
(define (unzip L) (cond
                    ((null? L) '(() ()))
                    (else (map list (car L) (car (cdr L))))
                    ))                  



;test cases
;(deepSum '(1 (2 3 4) (5) 6 7 (8 9 10) 11))
;(isSorted '(1 3 6 9 10))
;(mergeUnique2 '(1 2 3 4) '(3 4 5 6))
;(mergeUniqueN '((1 2 3) (3 4 5) (5 6)))
;(matrixMap (lambda (x) (+ 1 x)) '((0 1 2) (3 4 5)) ) 
;(matrixMap (lambda (x) (* x x)) '((1 2) (3 4)) ) 
;(avgOdd '(1 2 4 6))
;(unzip '((1 2) (3 4) (5 6)))


