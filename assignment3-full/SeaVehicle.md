# SeaVehicle

This document collects the specific requirements for validity and cost calculations for sea vehicles.

## Validation Rules

The following rules describe valid input data for the various vehicles.

1. All vehicles have a `color` field.  Valid colors are `red`, `green`, `blue`, `orange`, `yellow`, `purple`, `pink`,
   `black`, `white`, `silver`, `gold`.
2. There are three manufacturers, ACME, Consolidated Products, and Goliath Inc, which should be specified in the
   `manufacturer` field.
3. A vehicle's `type` field specifies the general kind of vehicle (`sailing vessel`, `powered vessel`) and after a comma, a
   subtype which conforms to the following rules;
    * sailing vessels can be `frigate`, `schooner`, or `xebec`;
    * powered vessels can be `jetski`, `yacht`, or `cargo ship`
4. An `engine` field specifies its manufacturer followed by its fuel type. Fuel types include `diesel`, `petrol`, and
   `electric`, all other fuel type values should be rejected. 
    * Only powered vessels have engines (and they must have one)
    * Jet skis can only have petrol engines.
    * Yachts can only have petrol or diesel engines.
    * Cargo ships must have diesel engines.
5. A `sails` field specifies the manufacturer, material, and number of the sails of a sailing vessel.  Numbers must be positive integers.  Valid sail 
   materials are `canvas`, `nylon`, and `mylar`.
    * Frigates have 10 sails.
    * Schooners have 6 sails.
    * Xebecs have 3 sails. 
6. The fields mentioned above (`type`, `color`, `manufacturer`, `engine`, optionally `sails`) must be present in the input,
   along with the `vehicle_id`.  `sails` should be present for sailing vessels (and not otherwise)
7. No fields should be repeated.

# Cost calculation

The *base* cost of a sea vehicle is obtained by summing the vehicle cost and the cost of all components.  
For components that have a quantity, like sails, this also needs to be taken into account.


Base costs by vehicle type:

| Vehicle          | Cost   |
|------------------|--------|
| Frigate          | 100000 |
| Xebec            | 5000   |
| Schooner         | 10000  |
| Jetski           | 1000   |
| Yacht            | 50000  |
| Cargo Ship       | 100000 |

Engine costs by fuel:

| Fuel   | Cost |
|--------|------|
| Petrol | 1000 |
| Diesel | 2000 |


Sail costs by material:

| Sail material | Cost per sail |
|---------------|---------------|
| canvas        | 500           |
| nylon         | 350           |
| mylar         | 900           |

Vehicles do not have to be valid in order for it to be possible to compute their cost: for example it may make no sense
to have a frigate with seven sails, but if it did the cost of the sails would be calculated by
multiplying the cost per sail by 7.  If a vehicle specification is invalid because of missing fields, the missing components
are omitted from the cost.  If a specification is invalid due to repeated fields, you only need to include the cost of the first
repeated field.  (In practice we are not usually going to be interested in the costs of invalid vehicles.)
