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
    (else L)
                               ))


(display(numbersToSum 30 '(5 4 6 10 4 2 1 5)))
(deepSum '(1 (2 3 4) (5) 6 7 (8 9 10) 11))
