package frc.robot;

public class States {

    public enum StartingConfig {
        HatchPannel, Cargo
    }

    public enum Positions {
        HatchLevelOne, HatchLevelTwo, HatchLevelThree,
        CargoRocketOne, CargoRocketTwo, CargoRocketThree, CargoIntakeElevated, CargoPlayerStationAndCargoShip,
        Floor, Stored,
        Estop
    }
}
