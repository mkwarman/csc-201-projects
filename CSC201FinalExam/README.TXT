PROJECT TITLE: CSC 201 Final Exam
VERSION: 1.7
AUTHORS: Matt Warman



CHANGELOG:


---- LoanManager.java ----

Version 1.7:
    Fixed issue causing all saves after the first to not be saved

Version 1.6:
    Improved error handeling when save file is found to be locked or inaccessable
    Separated save and exit functions
    Added save option to main menu

Version 1.5:
    Improved interest calculation
    Fixed bug that caused issues with pay off loans
    Fixed bug enabling paying less than monthly payment

Version 1.4
    Made changes to accomidate bug fixes in Loan.java

Version 1.3:
    Added comments

Version 1.2:
    Multiple bug fixes for deletion, calculation, payment, and more.

Version 1.1:
    Improved prompt and output format

Version 1.0:
    Functioning code
    
Version Pre-Alpha:
    Development and testing only

---------------------------------------------------------------------------------------------------------
    
---- Loan.java ----

Version 1.5:
    Bug fixes
    Added comments

Version 1.4:
    Improved interest calculation
    Added "This month's interest: [amount]"
    Changed monthly payment to reflect remaining balance when the remaining balance is less than
        what the monthly payment would have been

Version 1.3:
    Added comments
    Fixed inaccurate order of calculation in makePayment()
    Added calculateMonthlyPayment() to calculate monthly payment
    Fixed monthlyPayment to make it constant after calculation
    Made changes as necessary to implement monthly payment (re)calculation at the appropriate times

Version 1.2:
    Bug fixes

Version 1.1:
    Surrounded calculations with try{}-catch{} blocks
    
Version 1.0:
    Functioning Code
    
Version Pre-Alpha:
    Development and testing only