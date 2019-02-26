package frc.robot;

public class States {

<<<<<<< HEAD
<<<<<<< HEAD
    public enum StartingConfig {
        HatchPannel, Cargo
    }

    public enum Positions {
        HatchLevelOne, HatchLevelTwo, HatchLevelThree,
        CargoRocketOne, CargoRocketTwo, CargoRocketThree, CargoIntakeElevated, CargoPlayerStationAndCargoShip,
        Floor, Stored,
        Estop
=======
    public enum liftOperationState {
        ZEROING, RUNNING, MANUEL, 
    }

=======
    public enum liftOperationState {
        ZEROING, RUNNING, MANUEL, 
    }

>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
    public enum intakeOperationState {
        RUNNING, MANUEL
    }
    public enum liftPositions {
        BOTTOM, CARGO_INTAKING, MIDDLE, TOP
    }

    public enum intakePositions { 
        STORED, HATCH_SCORING, LIFTING, CARGO_SCORING_ANGLED, GROUND_PARALLEL, CARGO_INTAKING,
    }

    public enum Positions {
        HatchLevelOne, HatchLevelTwo, HatchLevelThree, HatchFloor,
        CargoRocketOne, CargoRocketTwo, CargoRocketThree, CargoCargoShip, CargoPlayerStation, CargoFloor,
        StartingPosition
<<<<<<< HEAD
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
=======
>>>>>>> parent of 0f7d36d... Created a sendable chooser, edited OI, took out similar positions, made intake in faster
    }
}
