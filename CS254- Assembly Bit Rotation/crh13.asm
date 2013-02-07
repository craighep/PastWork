;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  Craig Heptinstall crh13        ;;;
;;;  CS254 Assignment, Right Rotate ;;;
;;;  23rd November 2012		    ;;;
;;;				    ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

.orig x3000 

	
	LD R4, MASK ; Import mask for checking if the first bit is set
	LD R2, TWO  ; Used for dividng the given bits by two, in order to shift right
		
     TEST ADD R6, R0, #0 ; Test if coutn of rotations has reached 0, then exit
	BRz EXIT
	

	AND R3, R3, #0   ;
	ADD R0, R0, #-1 

	
	
     HALF_R1_TO_SHIFT_RIGHT; Loop halfing the bits to shift right
	

   	NOT R2, R2   ; Negate R2 
   	ADD R2, R2, #1 
        ADD R1, R1, R2   ; Subtract away 
    	NOT R2, R2   ; Negate R2 
   	ADD R2, R2, #1 

    	ADD R3, R3, #1   ; Use R3 as a tempory place to store value produced from 
    	AND R1, R1, #-1  ; halfing R1

   	BRp HALF_R1_TO_SHIFT_RIGHT	; If there is still a positive remaining in R1, keep minusing 2

     TEST_EVEN AND R6, R3, #1; Test whether the last bit is odd or even
        BRz CONTINUE         ; in order to round of half bits from division
    	ADD R3, R3, #-1 
    

     CONTINUE				
        AND R1, R1, R3
        AND R1, R3, R3;

	AND R5, R4, R1; Check if first bit is set for moving to most Sig.
	

     TEST_SIGNIFICAND ADD R5, R5, #0;	Test if the LSB is set or not then decide whether to 
		BRnz TEST					;   add in a 1 to the start.
	

	
	
     SIGNIFICAND_ADD ADD R1, R1, 15;	Add 15, then 1, in order to represent a 1 being added
		ADD R1, R1, 1;					to the MSB
		BR TEST
	
     EXIT RET;		Return via R7


	
TWO  .FILL X0002
MASK .FILL X0001

.END