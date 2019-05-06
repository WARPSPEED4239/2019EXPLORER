package frc.robot;

public class RobotMap {
  public static int drivetrainLeftOne = 1, //SparkMAX's
                    drivetrainLeftTwo = 2,
                    drivetrainLeftThree = 3,
                    drivetrainRightFour = 4,
                    drivetrainRightFive = 5,
                    drivetrainRightSix = 6;

  public static int elevatorMotorOne = 1, //SRX's
                    elevatorMotorTwo = 3,
                    wristMotor = 4,
                    
                    cargoIntakeMotor = 1, //SPX

                    canifier = 1; //CANifiers

  public static int liftBottomLimitSwitch = 2, //DIO
                    liftTop2To1LimitSwitch = 1,
                    liftTop3To2LimitSwitch = 0, 
                    wristBottomLimitSwitch = 3,
                    wristTopLimitSwitch = 4;

  public static int drivetrainShiftingSolenoidForward = 0, //Solenoids
                    drivetrainShiftingSolenoidReverse = 1,
                    hatchGrabberSolenoidForward = 2,
                    hatchGrabberSolenoidReverse = 3,
                    liftRodBackSolenoidForward = 4,
                    liftRodBackSolenoidReverse = 5,
                    liftRodsFrontSolenoidForward = 6,
                    liftRodsFrontSolenoidReverse = 7;
}
