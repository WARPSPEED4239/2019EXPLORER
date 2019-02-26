package frc.robot;

public class States { //TODO Implement states functionality

    public enum liftOperationState {
        ZEROING, RUNNING, MANUEL, 
    }

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
    }
}