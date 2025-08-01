# Assignment 3 assessment - README first

This IntelliJ project contains the full specification for the assessed stage of Assignment 3, in which you will implement 
version 2.0 of EVE: the EVE Vehicle Estimator.

There are four additional markdown files:
* GeneralRequirements.md: summarises the basic requirements for EVE 1.0 which were covered in the warmup.  These are
  collected in one place for ease of reference.  There are also a few important differences which are clearly marked in 
  a separate section.
* MotorVehicle.md: summarises the specific details of motor vehicles, which were also covered in the warmup, again for
  ease of reference.
* MotorVehicle.md: summarises the specific details of motor vehicles, which were also covered in the warmup, again for
  ease of reference.
* SeaVehicle.md: summarises specific details of sea vehicles
* FlyingVehicle.md: summarises specific details of sea vehicles
* acknowledgments.md: which we ask that you complete to indicate any assistance or collaboration on this assignment (during 
  the warmup phase).  This is not marked, but is helpful for us to understand how students worked during the warmup 
  phase and may be helpful for designing future similar assignments. 

**Important note:** Part of the specification of assignment 3 that you are to implement is provided by the automarker.  
This is because each student will be randomly assigned either SeaVehicles or FlyingVehicles to implement, and will also 
be assigned some customized validation and cost adjustment rules.

You should submit your current solution to the warmup to the assignment 3 automarker on Gradescope as soon as possible
to get these additional requirements.  The automarker will also provide feedback on how well your current solution meets
the general requirements.

If you have not worked on a solution to the warmup exercises yet, you can just submit an empty project (e.g. this one).


# Submitting

Submission is through GradeScope using the Assignment 3 autograder.

You may upload a zip file created from an IntelliJ project like this one.

You can submit as many times as as you want.  

The autograder does two important things:

1. Tells you your additional vehicle type to implement and additional validity/cost adjustment rules.
2. Runs tests which tell you whether your solution is correct (at least on the specific tested examples)

Your eventual grade will be entirely determined by the test results.  If all tests pass, your grade for Assignment 3 
will be 100%.  However, the feedback you get from the test runs does not tell you the specific test cases that were 
used.  

For the autograder to work properly, the following things should be true of your submission:

* It **MUST** have a main class called `EVEMain` with a `public static void main(String[] args)` method. 
* When called with an empty argument list, your submission **MUST** read inputs from `System.in` and write to `System.out` 
  following the specification(s). 
  (It is fine to also support reading from a file whose name is provided as a command line argument for your own convenience
  in testing, but the autograder will never do this.)
* You can create whatever additional classes, interfaces and code files you want, but all .java files **MUST** be   
  immediately inside the `src/` directory of the zip file you submit.  Do not use subdirectories or packages.  Java 
  files in other locations will not be compiled. 
* Your solution **MUST**  compile using Java 17 and **MUST NOT** assume any features in more recent versions of Java or rely on   
  libraries not part of the standard Java library.  We will compile only your submitted Java code in a sandboxed 
  environment without any libraries you might happen to add to the IntelliJ project.  

Please also submit a completed acknowledgments.md file with your final submission.