package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp
public class TeleOP2 extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");

        motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
//        float startPositionL = 0;
//        float startPositionR = 0;
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = -r * Math.cos(robotAngle) - rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = -r * Math.sin(robotAngle) - rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;


            motorWheelBL.setPower(v1);
            motorWheelBR.setPower(v2);
            motorWheelFL.setPower(v3);
            motorWheelFR.setPower(v4);
            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
            telemetry.update();

            telemetry.addData("Left_trigger", this.gamepad2.left_trigger);
            telemetry.addData("Right_trigger", this.gamepad2.right_trigger);

            telemetry.update();
        }
    }
}
