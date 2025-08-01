# General requirements

## EVE 1.0 recap

This section gathers requirements for EVE 1.0 that were presented in the warmup exercises.  Examples and additional
observations are not repeated here.

EVE is the EVE Vehicle Estimator.  It takes inputs consisting of vehicle specifications that describe things like the 
color, manufacturer, engine and wheels of a motor vehicle.  EVE does three main things:
1. Read inputs possibly not in the cleanest form, and standardise them to a clean form, for example avoiding
   extra whitespace, inconsistent punctuation and so on.
2. Analyze the vehicle specifications to determine whether they are valid, that is follow certain basic rules
   such as cars must have 4 wheels, etc.  
3. Compute an estimated cost for the vehicle.  By default this means adding up the costs of the components and the base 
   cost of a given vehicle type.



## Input format

Input to EVE is read from the standard input stream which you can access using `System.in` with the `Scanner` or
`BufferedReader` classes.  When there are no command line arguments, input should **only** be read from `System.in` and **only** be written to `System.out`.  

Each input consists of a sequence of vehicle entries, consisting of  a contiguous block of lines of the
form `key: value`.  Consecutive blocks are separated by at least one blank line.  More than one blank line in the
sequence is treated the same as a single blank line.  Keys are always a single word with no spaces or special characters,
just letters a-z, A-Z, or underscores.  Whitespace at the beginning of the value after the colon, or at the end of the value before the
newline, is not meaningful and should be ignored as well.  There should be exactly one colon per line.

**You can assume inputs follow these rules.  If some of these rules are violated, EVE's behavior is unspecified; for example EVE MAY  terminate with an exception:**
1. The `vehicle_id` should be the first  key-value pair in a block (that is, at the beginning of the input, or after one or more blank lines)
2. Keys should use only characters a-z, A-Z or '_'.
3. Blocks should not contain repeated keys.
4. Each line should have at most one colon.

There can be multiple vehicle entries, starting with the `vehicle_id` field.  The `vehicle_id` fields in a given
input need to be distinct positive integers, but they don't need to be consecutive.

Inputs are to be put into a standardised form.
The required standardised form is as follows:

1.  The vehicle entries have to be printed out in increasing order of the vehicle id.
2.  The fields are printed in alphabetical order, except the vehicle_id which must always appear first.
3.  Each entry is separated by exactly one blank line, even if there were multiple blank lines in the input.
4.  Keys should be printed with no whitespace before the beginning of the key or after the key and before the `:`symbol,
    then there should be exactly one whitespace character after the `:` and before the value, then the value itself
    (including any non-ignored whitespace in it), and then a newline.
5.  Numerical values should be standardised to remove any leading zeros.


Your implementation should accept inputs that are not in the above standardised form, for example due to excess whitespace
or leading zeros, but should always output vehicle records in standardised form.

## Handling of comma separated values

Values can have additional structure, for example an engine value consists of a manufacturer and a fuel type, separated
by a comma, and a wheels value consists of a manufacturer, a tyre type, and the number of wheels.

Whitespace
adjacent to a comma can also be ignored, and comma-separated values should be standardised so that there is no whitespace
immediately before each comma and exactly one space after each comma.  Whitespace in between words or other characters (not
adjacent to a comma) should still be preserved. 

Values that correspond to integers (including integers occurring in a comma separated list) should also be standardised 
by removing any leading zeros.

The order of the values in a comma separated list is significant and should not be changed.

## Validity and Costs

Each vehicle record has an associated validity (boolean value) and cost (integer value).  The rules for determining
validity and cost for each vehicle record are collected in the documents `MotorVehicle.md`, `SeaVehicle.md` and 
`FlyingVehicle.md`.


## Output format

The output format of EVE is very simple; it is essentially the standardised input format discussed already.  The main 
difference is that each vehicle specification is augmented with two fields: `valid` whose value is true or false,
and `cost` whose value is an integer calculated according to the cost rules for the relevant vehicle type.
(If these fields happen to already exist in the input, they should be
ignored and replaced with the calculated values.)

In EVE 2.0, both validity and cost calculations can be subject to additional customised rules, see the section 
`New for EVE 2.0` below.



# New for EVE 2.0

Our client wants to branch out from motor vehicles to sea-going and flying vehicles.
EVE 2.0 needs to support these additional kinds of vehicles:
* `SeaVehicle`s: sailing vessels, powered vessels, etc.
* `FlyingVehicle`s: airplanes, helicopters, hot air balloons, etc.

You will be randomly assigned one of these categories of vehicles to implement.
The client has come up with some additional rules
to take into account both for vehicle validity, and for adjusting costs of vehicles.  These apply both to the `MotorVehicle`s
you may already have implemented in the warmup, and your newly assigned vehicle category.

EVE 2.0 needs to support some *custom validation rules*.  For both motor vehicles and your new assigned vehicle cateory,
you will be given some additional rules for validation that need to be taken into account.

EVE 2.0 also needs to support *cost adjustments*.  These are additional rules for increasing or decreasing the cost of particular
vehicles when they meet certain criteria (e.g. discounts to incentivise buyers to make particular purcahses, or extra
costs/surcharges reflecting incompatibilities between manufacturers).  

Your assigned vehicle category, and associated customized rules for validity and cost adjustments, are provided when 
you submit a project to the autograder.  

There might be multiple custom validity rules that apply to a given vehicle.  If a vehicle violates **any** applicable 
validity rule then it is invalid.

There might also be multiple cost adjustment rules that apply to a given vehicle.  When this is the case, the total cost 
adjustment is the **sum** of all applicable cost adjustments.