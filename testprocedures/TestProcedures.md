# Carbon Knight Test Procedures

## Intake

### Successful Intake Completion
* Intake button is held by driver
    * Pneumatic cylinders extend
    * Intake rollers move inward
* Ball enters intake
    * Pneumatic cylinders retract
    * Intake rollers stop
    * Joystick rumbles
    * Ball is centered (2x)
    * Ball is loaded into shooter

### Intake Cancellation Procedure
* Intake button is held by driver
    * Pneumatic cylinders extend
    * Intake rollers move inward
* Intake button is let go
    * Pneumatic cylinders retract
    * Intake rollers move backward
* Small time passes
    * Intake roller stops

### Ignore inputs if ball is in shooter
* Ball is manually inserted into shooter by human
* Intake button is held by driver
    * No effects in subsystem

### Ball ejects when inside shooter
* Ball is loaded inserted into shooter
* Spit button is pressed by driver
    * Shooter roller moves outward
    * Intake roller moves inward
* Ball is received by intake
    * Shooter roller stops
    * Intake roller stops
    * Intake extends
* Small time passes
    * Intake roller moves outward
* Small time passes
    * Intake retracts
    * Intake roller stops


### Ball ejects when inside intake
* Ball is loaded inserted into intake
* Spit button is pressed by driver
    * Intake extends
* Small time passes
    * Intake roller moves outward
* Small time passes
    * Intake retracts
    * Intake roller stops

### Ball eject command does nothing when no ball found

