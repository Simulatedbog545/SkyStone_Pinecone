package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import static java.lang.System.nanoTime;

@TeleOp
public class TeleOP extends LinearOpMode{

    private DcMotor motorWheelFL;
    private DcMotor motorWheelFR;
    private DcMotor motorWheelBL;
    private DcMotor motorWheelBR;
    private Servo servoClamp;
    private Servo servoTray;
    private DcMotor motorLift;

    @Override
    public void runOpMode() throws InterruptedException {
        motorWheelFL = hardwareMap.get(DcMotor.class, "motorWheelFL");
        motorWheelFR = hardwareMap.get(DcMotor.class, "motorWheelFR");
        motorWheelBL = hardwareMap.get(DcMotor.class, "motorWheelBL");
        motorWheelBR = hardwareMap.get(DcMotor.class, "motorWheelBR");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        servoClamp = hardwareMap.get(Servo.class, "servoClamp");
        servoTray = hardwareMap.get(Servo.class, "servoTray");

	    motorWheelFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorWheelBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorWheelBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//	telemetry.addData("Status", "Initialized");
//        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        float startPositionL = 0;
        float startPositionR = 0;
        boolean close =false;
        boolean trayClose=false;
        boolean runteleOP=false;
//        int timer1 = 0;
//        int timer2 = 0;
//        int timer3 = 0;
        double V1= 0;
        double V2= 0;
        double V3= 0;
        double V4= 0;
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
//            telemetry.addData("Status", "Running");
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
            final double v1 = r * Math.cos(robotAngle) - rightX;
            final double v2 = -r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) - rightX;
            final double v4 = -r * Math.cos(robotAngle) - rightX;

            double c1=v1*0.5;
            double c2=v2*0.5;
            double c3=v3*0.5;
            double c4=v4*0.5;

            double WheelBL= motorWheelBL.getPower();
            double WheelBR= motorWheelBR.getPower();
            double WheelFL= motorWheelFL.getPower();
            double WheelFR= motorWheelFR.getPower();

            double errorBL= c1-WheelBL;
            double errorBR= c2-WheelBR;
            double errorFL= c3-WheelFL;
            double errorFR= c4-WheelFR;

            double KP= 0.8;

            boolean teleOP= gamepad1.left_stick_button;
            if(teleOP){
//                if(timer3 > 100) {
                    runteleOP = !runteleOP;
//                    timer3 = 0;
//                }
            }
            if(runteleOP){
                V1=v1;
                V2=v2;
                V3=v3;
                V4=v4;
            }
            else{
                V1= c1 + KP*errorBL;
                V2= c2 + KP*errorBR;
                V3= c3 + KP*errorFL;
                V4= c4 + KP*errorFR;
            }



            motorWheelBL.setPower(V1);
            motorWheelBR.setPower(V2);
            motorWheelFL.setPower(V3);
            motorWheelFR.setPower(V4);

//            telemetry.addData("FL Motor Power", motorWheelFL.getPower());
//            telemetry.addData("FR Motor Power", motorWheelFR.getPower());
//            telemetry.addData("BL Motor Power", motorWheelBL.getPower());
//            telemetry.addData("BR Motor Power", motorWheelBR.getPower());
//            telemetry.addData("v1",v1);
//            telemetry.addData("c1",c1);
//            telemetry.addData("errorBL",errorBL);
//            telemetry.addData("V1",V1);


          //  telemetry.update();

            float liftDown = this.gamepad1.left_trigger;
            float liftUp = this.gamepad1.right_trigger;
            boolean clamp = this.gamepad1.right_bumper;
            boolean tray = this.gamepad1.a;
            motorLift.setPower(liftUp-liftDown);
            if(clamp){
//                if(timer1 > 100) {
                    close = !close;
//                    timer1 = 0;
//                }
            }
            if(close){
                servoClamp.setPosition(1);
            }
            else{
                servoClamp.setPosition(0);
            }

            if(tray){
//                if(timer2 > 100) {
                    trayClose = !trayClose;
//                    timer2 = 0;
//                }
            }
            
            if(trayClose){
                servoTray.setPosition(0.25);
            }
            else{
                servoTray.setPosition(1);
            }
//            timer1++;
//            timer2++;
//            timer3++;
        }
    }
}
