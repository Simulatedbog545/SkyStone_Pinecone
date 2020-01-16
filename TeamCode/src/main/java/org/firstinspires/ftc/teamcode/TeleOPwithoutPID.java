package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TeleOPwithoutPID extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
//    private CRServo motorCollector;
//    private DcMotor motorExtenderRight;
//    private DcMotor motorExtenderLeft;
//    private DcMotor motorDeposit;
//    private DcMotor motorLift;
//    private CRServo motorRotator;.

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
//        motorCollector = hardwareMap.get(CRServo.class, "motorCollector");
//        motorExtenderRight = hardwareMap.get(DcMotor.class, "motorExtenderRight");
//        motorExtenderLeft = hardwareMap.get(DcMotor.class, "motorExtenderLeft");
//        motorDeposit = hardwareMap.get(DcMotor.class, "motorDeposit");
//        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
//        motorRotator = hardwareMap.get(CRServo.class, "motorRotator");
//
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
//        motorExtenderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorExtenderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorExtenderLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorExtenderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorDeposit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorDeposit.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


	telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        float startPositionL = 0;
        float startPositionR = 0;
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
//            float powerX = this.gamepad1.left_stick_x;
//            float powerY = this.gamepad1.left_stick_y;
//            float turnX = -this.gamepad1.right_stick_x;
//            float leftSide = ((powerY + powerX)/2 + turnX)/2;
//            float rightSide = ((powerY + powerX)/2 - turnX)/2;
//
//            motorWheelFL.setPower(-leftSide);
//            motorWheelFR.setPower(rightSide);
//            motorWheelBL.setPower(leftSide);
//            motorWheelBR.setPower(-rightSide);

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = -r * Math.cos(robotAngle) - rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = -r * Math.sin(robotAngle) - rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

//            double c1=v1*0.9;
//            double c2=v2*0.9;
//            double c3=v3*0.9;
//            double c4=v4*0.9;
//
//            double WheelBL= motorWheelBL.getPower();
//            double WheelBR= motorWheelBR.getPower();
//            double WheelFL= motorWheelFL.getPower();
//            double WheelFR= motorWheelFR.getPower();
//
//            double errorBL= c1-WheelBL;
//            double errorBR= c2-WheelBR;
//            double errorFL= c3-WheelFL;
//            double errorFR= c4-WheelFR;
//
//            double KP= 0.8;
//
//            double V1= c1 + KP*errorBL;
//            double V2= c2 + KP*errorBR;
//            double V3= c3 + KP*errorFL;
//            double V4= c4 + KP*errorFR;

            motorWheelBL.setPower(v1);
            motorWheelBR.setPower(v2);
            motorWheelFL.setPower(v3);
            motorWheelFR.setPower(v4);

            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
            telemetry.addData("v1",v1);
//            telemetry.addData("c1",c1);
//            telemetry.addData("errorBL",errorBL);
//            telemetry.addData("V1",V1);


            telemetry.update();

//            float left_trigger = this.gamepad2.left_trigger;
//            float right_trigger = this.gamepad2.right_trigger;
//            float extend = this.gamepad2.left_stick_y;
//            float armExtend = this.gamepad2.right_stick_y;
//            float liftDown = this.gamepad1.right_trigger;
//            float liftUp = this.gamepad1.left_trigger;
//            boolean collector = this.gamepad2.right_bumper;
//            boolean reverse = this.gamepad2.left_bumper;

//
//            motorExtenderRight.setPower(-extend);
//            motorExtenderLeft.setPower(extend);
//            if(motorExtenderRight.getCurrentPosition() > motorExtenderLeft.getCurrentPosition()){
//                motorExtenderRight.setPower((-extend)* (motorExtenderLeft.getCurrentPosition()/motorExtenderRight.getCurrentPosition()));
//            }
//            else if(motorExtenderRight.getCurrentPosition() < motorExtenderLeft.getCurrentPosition()){
//                motorExtenderLeft.setPower((extend)* (motorExtenderRight.getCurrentPosition()/motorExtenderLeft.getCurrentPosition()));
//            }
//            motorDeposit.setPower(armExtend);
//
//            motorRotator.setPower(right_trigger/2-left_trigger/2);
//            motorLift.setPower(liftUp);
//            motorLift.setPower(-liftDown);
//            if(collector){
//                motorCollector.setPower(-0.8);
//            }
//            else{
//                motorCollector.setPower(0);
//            }
//            if(reverse){
//                motorCollector.setPower(0.8);
//            }
//            else{
//                motorCollector.setPower(0);
//            }
            telemetry.addData("Left_trigger", this.gamepad2.left_trigger);
            telemetry.addData("Right_trigger", this.gamepad2.right_trigger);
//            telemetry.addData("motorLift", motorLift.getCurrentPosition());
//            telemetry.addData("motorExtenderLeft", motorExtenderLeft.getCurrentPosition());
//            telemetry.addData("motorExtenderRight", motorExtenderRight.getCurrentPosition());
//            telemetry.addData("motorDeposit", motorDeposit.getCurrentPosition());
            telemetry.update();
        }
    }
}
