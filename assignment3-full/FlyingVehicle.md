# FlyingVehicle

This document collects the specific requirements for validity and cost calculations for flying vehicles.

## Validation Rules

The following rules describe valid input data for the various vehicles.

1. All vehicles have a `color` field.  Valid colors are `red`, `green`, `blue`, `orange`, `yellow`, `purple`, `pink`,
   `black`, `white`, `silver`, `gold`.
2. There are three manufacturers, ACME, Consolidated Products, and Goliath Inc, which should be specified in the
   `manufacturer` field.
3. A vehicle's `type` field specifies the general kind of vehicle (`airplane`, `helicopter`, `hot air balloon`) and after a comma, a
   possibly a subtype which conforms to the following rules;
    * airplanes can be `passenger`, `cargo`, or `biplane`
    * helicopter can be `commercial` or `rescue`
    * we do not differentiate subtypes of hot air balloon
4. An `engine` field specifies its manufacturer followed by its fuel type. Fuel types for flying vehicles  include `kerosene`, `petrol`, and
   `propane`, all other fuel type values should be rejected.
    * Airplanes must have kerosene fuelled engines.
    * Helicopter engines can be fuelled by either kerosene or petrol.
    * Hot air balloons must have propane fuelled engines.
5. A `wings` field specifies the manufacturer, material, and number of the wings of an airplane.  Numbers must be positive integers.  Valid wing
   materials are `canvas` or `aluminium`.
    * Passenger and cargo airplanes have 2 wings.
    * Biplanes have 4 wings.
6. A `rotors` field specifies the manufacturer and number of rotors of a helicopter.
   * A rescue helicopter can have 2 or 4 rotors.
   * A commercial helicopter must have 2 rotors.
6. The fields mentioned above (`type`, `color`, `manufacturer`, `engine`, optionally `wings` or `rotors`) must be present in the input,
   along with the `vehicle_id`.  `wings` or `rotors` should be present for airplanes or helicopters respectively (and not otherwise).
7. No fields should be repeated.

# Cost calculation

The *base* cost of a flying vehicle is obtained by summing the vehicle cost and the cost of all components.  
For components that have a quantity, like wings or rotors, this also needs to be taken into account.


Base costs by vehicle type:

| Vehicle               | Cost   |
|-----------------------|--------|
| Passenger plane       | 150000 |
| Cargo plane           | 200000 |
| Biplane               | 50000  |
| Rescue helicopter     | 175000 |
| Commercial helicopter | 100000 |
| Hot air balloon       | 10000  |

Engine costs by fuel:

| Fuel     | Cost |
|----------|------|
| Petrol   | 1000 |
| Propane  | 500  |
| Kerosene | 4000 |


Rotors: cost 1500 each

Vehicles do not have to be valid in order for it to be possible to compute their cost: for example it may make no sense
to have an airplane with seven wings, but if it did the cost of the wings would be calculated by
multiplying the cost per wing by 7.  If a vehicle specification is invalid because of missing fields, the missing components
are omitted from the cost.  If a specification is invalid due to repeated fields, you only need to include the cost of the first
repeated field.  (In practice we are not usually going to be interested in the costs of invalid vehicles.)
