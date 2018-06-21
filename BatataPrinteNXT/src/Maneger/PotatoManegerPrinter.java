package Maneger;


import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.Touch;

public class PotatoManegerPrinter {

	private static int CanetaSobeDesce_MotorRotate_100 = 0; //movimento completo
	private static int CanetaLateral_MotorRotate_100 = 0;//para mover 10 cm 
	private static int Papel_MotorRotate_100 = 0; //para mover 10 cm
			

	private static NXTRegulatedMotor motorCanetaSobeDesce =Motor.A;
	private static NXTRegulatedMotor motorCanetaLateral = Motor.B;
	private static NXTRegulatedMotor motorPapel = Motor.C;
	
	private static LightSensor sensorLuz = new LightSensor(SensorPort.S1);
	private static TouchSensor sensorToque = new TouchSensor(SensorPort.S2);

	private int velocidadeCanetaSobeDesce = 100;
	private int velocidadeCanetaLateral = 100;
	private int velocidadePapel = 100;
	
	private int valorLuzComPapel =100;
	
	public enum CanetaAcao{
		DESCE,
		SOBE,
		MOVE_DIREITA,
		MOVE_ESQUERDA;
	}	

	
	
	

	
	private int getRotateCanetaSobeDesce_VelX(int velX) {
		
		int Xrotate =  (velX * CanetaSobeDesce_MotorRotate_100)/100 ;
	
	return Xrotate;
	
	}
	
	private int getRotateCanetaLateral_VelX(int velX ) {
		
		int Xrotate = (velX * CanetaLateral_MotorRotate_100) * 100;	
		return Xrotate;
		
		
	}
	private int getRotatePapel_VelX(int velX) {
		int Xrotate = (velX * Papel_MotorRotate_100) * 100;	
		return Xrotate;
		
	}
	
	
	
	private void rotacionaMotorCanetaSobeDesce(int rotate) {
		motorCanetaSobeDesce.rotate(rotate);
		
	}
	private void rotacionaMotorCanetaLateral(int rotate) {
		motorCanetaLateral.rotate(rotate);
	}
	private void rotacionaMotorPapel(int rotate) {
		motorPapel.rotate(rotate);
	}
	
	public void acaoMoveCanetaSobe() {
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		rotacionaMotorCanetaSobeDesce(rotateX);
		
	}
	public void acaoMoveCanetaDesce() {
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		rotacionaMotorCanetaSobeDesce(-rotateX);
		
	}
	public void acaoMoveCanetaLateralDireita() {
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		
		rotacionaMotorCanetaLateral(rotateX);
		
	}
	public void acaoMoveCanetaLateralEsquerda() {
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		
		rotacionaMotorCanetaLateral(-rotateX);
		
	}
	public void acaoMovePapelFrente(int  un) {
		int rotateX = getRotatePapel_VelX(getRotatePapel_VelX(velocidadePapel));		
		rotacionaMotorPapel(rotateX * un);
	}
	public void acaoMovePapelTraz(int un) {
		int rotateX = getRotatePapel_VelX(getRotatePapel_VelX(velocidadePapel));		
		rotacionaMotorPapel(-rotateX * un);
	}
	
	public void acaoEjetarPapel() {
		while(!verificaExistePapel()) {
		motorPapel.forward();
		}
		motorPapel.stop();
		
	}
	
	public void PuxarEjetarPapel() {
		while(verificaExistePapel()) {
		motorPapel.forward();
		}
		motorPapel.stop();
		
	}
	
	
	public boolean verificaExistePapel() {
		
		int valorLuz = sensorLuz.getLightValue();
		
		if(valorLuz >= valorLuzComPapel) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public int getVelocidadeCanetaLateral() {
		return velocidadeCanetaLateral;
	}
	public void setVelocidadeCanetaLateral(int velocidadeCanetaLateral) {
		this.velocidadeCanetaLateral = velocidadeCanetaLateral;
	}
	public int getVelocidadeCanetaSobeDesce() {
		return velocidadeCanetaSobeDesce;
	}
	public void setVelocidadeCanetaSobeDesce(int velocidadeCanetaSobeDesce) {
		this.velocidadeCanetaSobeDesce = velocidadeCanetaSobeDesce;
	}

	

	
	
	

}
