package frc.robot;

public class States {

    public boolean PositionControlState;
    
    public enum StartingConfig {
        HatchPannel, Cargo
    }

    public enum Positions {
        HatchLevelOne, HatchLevelTwo, HatchLevelThree,
        CargoRocketOne, CargoRocketTwo, CargoRocketThree, CargoIntakeElevated, CargoPlayerStationAndCargoShip,
        Floor, Stored, EndgamePrep, EndgameDown,
        Estop
    }

    public boolean getPostionControlState() {
        return PositionControlState;
    }

    public void setPositionControlState(boolean state) {
        PositionControlState = state;
    }
}
