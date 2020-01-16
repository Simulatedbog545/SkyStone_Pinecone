package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.*;

@Autonomous
public class AutonomousBlue extends LinearOpMode {


	private DcMotor motorWheelFL;
	private DcMotor motorWheelFR;
	private DcMotor motorWheelBL;
	private DcMotor motorWheelBR;
	private CRServo motorCollector;
	private DcMotor motorExtenderRight;
	private DcMotor motorExtenderLeft;
	private DcMotor motorDeposit;
	private DcMotor motorLift;
	private CRServo motorRotator;

	//private ColorSensor sensor;
	//private Servo arm;

	//	private final double drivePidKp = 1;	 // Tuning variable for PID.
	//	private final double drivePidTi = 1.0;   // Eliminate integral error in 1 sec.
	//	private final double drivePidTd = 0.1;   // Account for error in 0.1 sec.
	//	// Protect against integral windup by limiting integral term.
	//	private final double drivePidIntMax = 1.0;  // Limit to max speed.
	//	private final double driveOutMax = 1.0;  // Motor output limited to 100%.


	@Override
	public void runOpMode() throws InterruptedException {
		motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
		motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
		motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
		motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
		motorCollector = hardwareMap.get(CRServo.class, "motorCollector");
		motorExtenderRight = hardwareMap.get(DcMotor.class, "motorExtenderRight");
		motorExtenderLeft = hardwareMap.get(DcMotor.class, "motorExtenderLeft");
		motorDeposit = hardwareMap.get(DcMotor.class, "motorDeposit");
		motorLift = hardwareMap.get(DcMotor.class, "motorLift");
		motorRotator = hardwareMap.get(CRServo.class, "motorRotator");

		motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorCollector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorCollector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorExtenderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorExtenderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorExtenderLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorExtenderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorDeposit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorDeposit.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		telemetry.addData("Status", "Initialized");
		telemetry.update();
		// Wait for the game to start (driver presses PLAY)
		waitForStart();
		//clawMoveForward(200);
		while(opModeIsActive()){
            motorDeposit.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lowerBot(1);
        }


	}

	public void lowerBot(int rot) {
//       while(motorDeposit.getCurrentPosition() < rot) {
////           motorDeposit.setPower(1);
////           telemetry.addData("motorDeposit", motorDeposit.getCurrentPosition());
////       }

        boolean run = true;

        while (run) {
            if(motorDeposit.getCurrentPosition() >= rot){
                motorDeposit.setPower(0);
                run = false;
            }
            else motorDeposit.setPower(1);
        }
	}
	public void ExtraLower(int rot, int enc){
        double diff = enc-rot;
        if(diff < 0){
            diff *= -1;
        }
        while(diff > 0){
            diff = motorDeposit.getCurrentPosition()-rot;
            motorDeposit.setPower(diff);
        }
    }
	public void claw2(int deg) {
		int startR = motorExtenderRight.getCurrentPosition();
		while ((motorExtenderRight.getCurrentPosition() - startR) % 360 < deg) {
			motorExtenderRight.setPower(-0.05);
			motorExtenderLeft.setPower(0.05);
			if (motorExtenderRight.getCurrentPosition() > motorExtenderLeft.getCurrentPosition()) {
				motorExtenderRight.setPower((-0.05) * (motorExtenderLeft.getCurrentPosition() / motorExtenderRight.getCurrentPosition()));
			} else if (motorExtenderRight.getCurrentPosition() < motorExtenderLeft.getCurrentPosition()) {
				motorExtenderLeft.setPower((0.05) * (motorExtenderRight.getCurrentPosition() / motorExtenderLeft.getCurrentPosition()));
			}
		}
		motorExtenderRight.setPower(0);
		motorExtenderLeft.setPower(0);
	}
	public void clawMoveForward(int deg) {
		int rot = deg / 360;
		int extra = deg % 360;
		for (int i = 0; i < rot; i++) {
			int startR = motorExtenderRight.getCurrentPosition();
			int startL = motorExtenderLeft.getCurrentPosition();
			boolean moved = false;
			while (!moved && !(motorExtenderRight.getCurrentPosition() - startR <= 0.2 && motorExtenderRight.getCurrentPosition() - startR >= -0.2 && motorExtenderLeft.getCurrentPosition() - startL <= 0.2 && motorExtenderLeft.getCurrentPosition() - startL >= -0.2)) {
				motorExtenderRight.setPower(0.05);
				motorExtenderLeft.setPower(-0.05);
				if (motorExtenderRight.getCurrentPosition() - startR - motorExtenderLeft.getCurrentPosition() + startL > 0.2) {
					motorExtenderRight.setPower(0);
				} else if (motorExtenderRight.getCurrentPosition() - startR - motorExtenderLeft.getCurrentPosition() + startL < -0.2) {
					motorExtenderLeft.setPower(0);
				}
				if (motorExtenderRight.getCurrentPosition() - startR > 180 || motorExtenderRight.getCurrentPosition() - startR < -180) moved = true;
			}
		}
		motorExtenderRight.setPower(0);
		motorExtenderLeft.setPower(0);
		int startR = motorExtenderRight.getCurrentPosition();
		int startL = motorExtenderLeft.getCurrentPosition();
		int start = motorDeposit.getCurrentPosition();
		while (!(motorExtenderRight.getCurrentPosition() - startR - extra <= 0.2 && motorExtenderRight.getCurrentPosition() - startR - extra >= -0.2 && motorExtenderLeft.getCurrentPosition() - startL - extra <= 0.2 && motorExtenderLeft.getCurrentPosition() - startL - extra>= -0.2)) {
			motorExtenderRight.setPower(0.05);
			motorExtenderLeft.setPower(-0.05);
			if (motorExtenderRight.getCurrentPosition() - startR - motorExtenderLeft.getCurrentPosition() + startL > 0.2) {
				motorExtenderRight.setPower(0);
			} else if (motorExtenderRight.getCurrentPosition() - startR - motorExtenderLeft.getCurrentPosition() + startL < -0.2) {
				motorExtenderLeft.setPower(0);
			}
		}
		motorExtenderRight.setPower(0);
		motorExtenderLeft.setPower(0);
	}
}
