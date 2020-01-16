package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonomousDrive extends LinearOpMode {

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;

//    private final double drivePidKp = 1;     // Tuning variable for PID.
//    private final double drivePidTi = 1.0;   // Eliminate integral error in 1 sec.
//    private final double drivePidTd = 0.1;   // Account for error in 0.1 sec.
//    // Protect against integral windup by limiting integral term.
//    private final double drivePidIntMax = 1.0;  // Limit to max speed.
//    private final double driveOutMax = 1.0;  // Motor output limited to 100%.


    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");

        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (opModeIsActive()) {
            motorWheelFL.setPower(-0.5);
            motorWheelFR.setPower(0.5);
            motorWheelBL.setPower(-0.5);
            motorWheelBR.setPower(0.5);
            sleep(1000);
            motorWheelFL.setPower(0);
            motorWheelFR.setPower(0);
            motorWheelBL.setPower(0);
            motorWheelBR.setPower(0);
        }
    }
}