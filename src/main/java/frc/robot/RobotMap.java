package frc.robot;

public class RobotMap {
  public static int drivetrainLeftOne = 1, //SparkMAX's
                    drivetrainLeftTwo = 2,
                    drivetrainLeftThree = 3,
                    drivetrainRightFour = 4,
                    drivetrainRightFive = 5,
                    drivetrainRightSix = 6;

  public static int elevatorMotorOne = 1, //SRX's
                    liftWheelsMotor = 2,
                    elevatorMotorTwo = 3,
                    wristMotor = 4,
                    
                    cargoIntakeMotor = 1, //SPX

                    canifier = 1, //CANifiers

                    pigeonIMU = 1; //Pigeon IMU

  public static int liftBottomLimitSwitch = 2, //DIO
                    liftTop2To1LimitSwitch = 1,
                    liftTop3To2LimitSwitch = 0, 
                    wristBottomLimitSwitch = 3,
                    wristTopLimitSwitch = 4;

  public static int drivetrainShiftingSolenoidForward = 0, //Solenoids
                    drivetrainShiftingSolenoidReverse = 1,
                    hatchGrabberSolenoidForward = 2,
                    hatchGrabberSolenoidReverse = 3,
                    liftWheelsSolenoidForward = 4,
                    liftWheelsSolenoidReverse = 5,
                    wristLockSolenoidForward = 6,
                    wristLockSolenoidReverse = 7;
}
