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
	
	private CanetaAcao statusAtualSobeDesce = CanetaAcao.SOBE;
	
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
	
	/**
	 * Move caneta o suficiente para sair do papel, se a caneta já estiver posicionada na posição não faz nada
	 */
	public void acaoMoveCanetaSobe() {
				
		if(statusAtualSobeDesce != CanetaAcao.SOBE) {
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		rotacionaMotorCanetaSobeDesce(rotateX);
		statusAtualSobeDesce = CanetaAcao.SOBE;
		}
		
	}
	public void acaoMoveCanetaDesce() {
		if(statusAtualSobeDesce != CanetaAcao.DESCE) {
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		rotacionaMotorCanetaSobeDesce(-rotateX);
		statusAtualSobeDesce = CanetaAcao.DESCE;
		}
		
	}
	
	
/**
 * Executa o movimen to lateral que foi definido para ser o proximo para escrita 
 */
	public void acaoMoveCanetaProximo(int un) {
		acaoMoveCanetaLateralEsquerda(un);
	}

	
	public void acaoMoveCanetaLateralDireita(int un) {
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		//calibrar  com base na velocidade
		rotacionaMotorCanetaLateral(rotateX * un);
		
	}
	
	public void acaoMoveCanetaLateralDireita(int un, int vel) {
		
		setVelocidadeCanetaLateral(vel);
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		//calibrar  com base na velocidade
		rotacionaMotorCanetaLateral(rotateX * un);
		
	}
	public void acaoMoveCanetaLateralEsquerda(int un) {
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		
		rotacionaMotorCanetaLateral(-rotateX * un);
		
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
		this.motorCanetaLateral.setSpeed(velocidadeCanetaLateral);
		this.velocidadeCanetaLateral = velocidadeCanetaLateral;
	}
	public int getVelocidadeCanetaSobeDesce() {
		return velocidadeCanetaSobeDesce;
	}
	public void setVelocidadeCanetaSobeDesce(int velocidadeCanetaSobeDesce) {
		this.motorCanetaSobeDesce.setSpeed(velocidadeCanetaSobeDesce);
		this.velocidadeCanetaSobeDesce = velocidadeCanetaSobeDesce;
	}
	


	
	
	

}
