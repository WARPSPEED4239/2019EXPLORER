package frc.robot;

public class States { //TODO Implement states functionality

    public enum StartingConfig {
        HatchPannel, Cargo
    }

    public enum OperationStates {
        Zeroing, Running, Manuel, 
    }

    public enum Positions {
        HatchLevelOne, HatchLevelTwo, HatchLevelThree, HatchFloor,
        CargoRocketOne, CargoRocketTwo, CargoRocketThree, CargoPlayerStationAndCargoShip, CargoFloor,
        StoredPosition
    }
}
