# MotorVehicle

This document collects the specific requiements for validity and cost calculations for motor vehicles.

## Validation Rules

The following rules describe valid input data for the various vehicles.

1. All vehicles have a `color` field.  Valid colors are `red`, `green`, `blue`, `orange`, `yellow`, `purple`, `pink`,
   `black`, `white`, `silver`, `gold`.
2. There are three manufacturers, ACME, Consolidated Products, and Goliath Inc, which should be specified in the
   `manufacturer` field.
3. A vehicle's `type` field specifies the general kind of vehicle (`car`, `truck`, `motorcycle`) and after a comma, a
   subtype which conforms to the following rules;
    * cars can be `hatchback`, `sedan`, or `convertible`;
    * trucks can be `pickup` or `eighteen wheeler`, and
    * motorcycles can be `sport` or `touring`.
4. An `engine` field specifies its manufacturer followed by its fuel type. Fuel types include `diesel`, `petrol`, and
   `electric`, all other fuel type values should be rejected.
    * Cars can have any engine fuel while motorcycles can only be petrol fueled and trucks cannot be electric.
5. A `wheels` field specifies its manufacturer, type, and number.  Numbers must be positive integers.  Wheel types are
   either `winter`, `summer`, or `all-weather`.
    * Cars must have 4 wheels.
    * Pickup trucks which have 4 or 6 wheels and eighteen wheeler trucks must have 18 wheels.
    * Motorcycles must have 2 wheels.
6. All of the fields mentioned above (`type`, `color`, `manufacturer`, `engine`, `wheels`) must be present in the input,
   along with the `vehicle_id`.
7. No fields should be repeated.

# Cost calculation

The *base* cost of a motor vehicle is obtained by summing the vehicle cost and the cost of all components.  
For components that have a quantity, like wheels/tyres,
this also needs to be taken into account.  


Base costs by vehicle type:

| Vehicle            | Cost  |
|--------------------|-------|
| Hatchback          | 8000  |
| Sedan              | 12000 |
| Convertible        | 20000 |
| Sport Motorcycle   | 16000 |
| Touring Motorcycle | 9000  |
| Pickup Truck       | 20000 |
| Eighteen Wheeler   | 35000 |

Engine costs by fuel:

| Fuel     | Cost |
|----------|------|
| Petrol   | 1000 |
| Diesel   | 2000 |
| Electric | 5000 |


Wheel costs by type:

| Type type   | Cost per tire |
|-------------|---------------|
| summer      | 100           |
| winter      | 120           |
| all-weather | 150           |

Vehicles do not have to be valid in order for it to be possible to compute their cost: for example it may make no sense
to have a motorcycle with seven wheels, but if it did the cost of the wheels would be calculated by
multiplying the cost per wheel by 7.  If a vehicle specification is invalid because of missing fields, the missing components
are omitted from the cost.  If a specification is invalid due to repeated fields, you only need to include the cost of the first
repeated field.  (In practice we are not usually going to be interested in the costs of invalid vehicles.)
