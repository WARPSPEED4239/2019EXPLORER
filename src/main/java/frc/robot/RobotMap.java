package frc.robot;

public class RobotMap {
  public static int drivetrainLeftOne = 1, //SparkMAX's
                    drivetrainLeftTwo = 2,
                    drivetrainLeftThree = 3,
                    drivetrainRightFour = 4,
                    drivetrainRightFive = 5,
                    drivetrainRightSix = 6;

  public static int liftMotor1 = 1, //SRX's
                    liftMotor2 = 2,
                    wristMotor = 3,
                    rampMotor = 4,

                    cargoIntakeMotor = 1, //SPX

                    canifer = 1, //CANifier

                    pigeonIMU = 1;

  public static int drivetrainShiftingSolenoidForward = 0, //Solenoids
                    drivetrainShiftingSolenoidReverse = 1,
                    hatchGrabberSolenoidForward = 2,
                    hatchGrabberSolenoidReverse = 3,
                    liftWheelsSolenoidFrontForward = 4,
                    liftWheelsSolenoidFrontReverse = 5,
                    liftWheelsSolenoidBackForward = 6,
                    liftWheelsSolenoidBackReverse = 7;

  public static int liftTopLimitSwitch = 0, //DIO
                    liftBottomLimitSwitch = 1;
}
