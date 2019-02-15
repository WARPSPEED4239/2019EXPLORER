package frc.robot;

public class RobotMap {
  public static int drivetrainLeftOne = 1, //SparkMAX's
                    drivetrainLeftTwo = 2,
                    drivetrainLeftThree = 3,
                    drivetrainRightFour = 4,
                    drivetrainRightFive = 5,
                    drivetrainRightSix = 6;

  public static int elevatorMotorOne = 1, //SRX's
                    rampMotor = 2,
                    elevatorMotorTwo = 3,
                    wristMotor = 4,
                    
                    cargoIntakeMotor = 1, //SPX

                    canifier = 1, //CANifiers

                    pigeonIMU = 1; //Pigeon IMU

  public static int liftBottomLimitSwitch = 0,
                    liftTop2To1LimitSwitch = 1,
                    liftTop3To2LimitSwitch = 2;

  public static int drivetrainShiftingSolenoidForward = 0, //Solenoids
                    drivetrainShiftingSolenoidReverse = 1,
                    hatchGrabberSolenoidForward = 2,
                    hatchGrabberSolenoidReverse = 3,
                    liftWheelSolenoidForward = 4,
                    liftWheelSolenoidReverse = 5,
                    rampSolenoidForward = 6,
                    rampSolenoidReverse = 7;
}
