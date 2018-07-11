package Maneger;


import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.Touch;

public class PotatoManegerPrinter {

	public PotatoManegerPrinter(int velocidadeCanetaSobeDesce,
			int velocidadeCanetaLateral, int velocidadePapel
			) {
	
	setVelocidadeCanetaSobeDesce(velocidadeCanetaSobeDesce);
	setVelocidadeCanetaLateral(velocidadeCanetaLateral);
	
	}

	private static int CanetaSobeDesce_MotorRotate_100 = 20; //movimento Desenho
	public static int CanetaSobeDesce_MotorRotate_100_Completo = 60; //MovimentoLimite
	public static int CanetaSobeDesce_MotorRotate_100_Incial = 43; //MovimentoLimite
	
	
	private static int CanetaLateral_MotorRotate_100 = 20;//para mover 
	private static int Papel_MotorRotate_100 = 20; //para mover 10 cm
	
	
	public static int Papel_largura_UN = 55;
			

	private static NXTRegulatedMotor motorCanetaSobeDesce =Motor.A;
	private static NXTRegulatedMotor motorCanetaLateral = Motor.B;
	private static NXTRegulatedMotor motorPapel = Motor.C;
	
	private static LightSensor sensorLuz = new LightSensor(SensorPort.S1);
	private static TouchSensor sensorToque = new TouchSensor(SensorPort.S2);

	private int velocidadeCanetaSobeDesce = 100;
	private int velocidadeCanetaLateral = 100;
	private int velocidadePapel = 100;
	
	private int valorLuzComPapel =45;
	
	private CanetaAcao statusAtualSobeDesce = CanetaAcao.SOBE;
	
	public enum CanetaAcao{
		DESCE,
		SOBE,
		DESCE_METADE,
		SOBE_METADE,
		MEIO,
		MOVE_DIREITA,
		MOVE_ESQUERDA;
	}	
	
	/**
	 * Prepara a caneta para o inicio da impreção
	 */
	public void AcaoMovimentaInicial(){
		acaoMoveCanetaDesce(CanetaSobeDesce_MotorRotate_100_Incial);
		acaoMoveCanetaLateralDireita(Papel_largura_UN/2);// movimenta a caneta para direita o maximo possivel
	}
	
	public void AcaoMovimentaFinal(){		
		acaoMoveCanetaLateralEsquerda(Papel_largura_UN);
		acaoMoveCanetaLateralDireita(Papel_largura_UN/2);//posiciona caneta ao meio
		acaoMoveCanetaSobe(CanetaSobeDesce_MotorRotate_100_Completo);
	}

	public void acaoMoveCanetaProximaLinha(){
		acaoMoveCanetaSobe();
		acaoMovePapelTraz(2);
		acaoMoveCanetaLateralDireita(Papel_largura_UN/2);
	}
		


	private int getRotateCanetaSobeDesce_VelX(int velX) {
		
		int Xrotate =  (velX * CanetaSobeDesce_MotorRotate_100)/100 ;
	
	return Xrotate;
	
	}
	
	private int getRotateCanetaLateral_VelX(int velX ) {
		
		int Xrotate = (velX * CanetaLateral_MotorRotate_100) / 100;	
		return Xrotate;
		
		
	}
	private int getRotatePapel_VelX(int velX) {
		int Xrotate = (velX * Papel_MotorRotate_100) / 100;	
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
	 * Movee  levanta totalmente a caneta do papel, se a caneta já estiver posicionada na posição não faz nada
	 */
	public void acaoMoveCanetaSobe() {
				
		if(statusAtualSobeDesce != CanetaAcao.SOBE) {
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		statusAtualSobeDesce = CanetaAcao.SOBE;
		rotacionaMotorCanetaSobeDesce(-rotateX);
		
		}
		
	}
	
	public void acaoMoveCanetaSobe(int rotateX) {
		
		if(statusAtualSobeDesce != CanetaAcao.SOBE) {				
		statusAtualSobeDesce = CanetaAcao.SOBE;
		rotacionaMotorCanetaSobeDesce(-rotateX);
		
		}
		
	}	
	


	public void setStatusAtualSobeDesce(CanetaAcao statusAtualSobeDesce) {
		this.statusAtualSobeDesce = statusAtualSobeDesce;
	}
	
	/**
	 * Move caneta para enconstar no papel considerando que ela estava fora
	 */
	

	public void acaoMoveCanetaDesce() {
		if(statusAtualSobeDesce != CanetaAcao.DESCE) {
		
		int rotateX = getRotateCanetaSobeDesce_VelX(velocidadeCanetaSobeDesce);		
		statusAtualSobeDesce = CanetaAcao.DESCE;		
		
		rotacionaMotorCanetaSobeDesce(rotateX);
		
		}
		
	}
	
	public void acaoMoveCanetaDesce(int rotateX) {
		if(statusAtualSobeDesce != CanetaAcao.DESCE) {					
			//statusAtualSobeDesce = CanetaAcao.DESCE;				
		rotacionaMotorCanetaSobeDesce(rotateX);
		
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
		System.out.println(("RotateX" + rotateX));
		rotacionaMotorCanetaLateral(-rotateX * un);
		
	}
	
	public void acaoMoveCanetaLateralDireita(int un, int vel) {
		
		setVelocidadeCanetaLateral(vel);
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		//calibrar  com base na velocidade
		rotacionaMotorCanetaLateral(rotateX * un);
		
	}
	public void acaoMoveCanetaLateralEsquerda(int un) {
		System.out.println("proximo" + un);
		int rotateX = getRotateCanetaLateral_VelX(velocidadeCanetaLateral);		
		rotacionaMotorCanetaLateral(rotateX * un);
		
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
	
	public void PuxarPapel() {
		while(verificaExistePapel()) {
		motorPapel.forward();
		}
		motorPapel.stop();
		
	}
	
	
	public boolean verificaExistePapel() {
		
		int valorLuz = sensorLuz.getLightValue();
		System.out.println("SesorLuz" + valorLuz);
		
		if(valorLuz >= valorLuzComPapel) {
			return true;
		}else {
			return false;
		}
	}
	
	
	private String valorLuz() {
		// TODO Auto-generated method stub
		return null;
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
	public void setVelocidadePapel(int velocidadePapel) {
		
		this.motorPapel.setSpeed(velocidadePapel);
		this.velocidadePapel = velocidadePapel;
	}

	public static int getCanetaSobeDesce_MotorRotate_100() {
		return CanetaSobeDesce_MotorRotate_100;
	}

	public static void setCanetaSobeDesce_MotorRotate_100(
			int canetaSobeDesce_MotorRotate_100) {
		CanetaSobeDesce_MotorRotate_100 = canetaSobeDesce_MotorRotate_100;
	}

	public static int getCanetaLateral_MotorRotate_100() {
		return CanetaLateral_MotorRotate_100;
	}

	public static void setCanetaLateral_MotorRotate_100(
			int canetaLateral_MotorRotate_100) {
		CanetaLateral_MotorRotate_100 = canetaLateral_MotorRotate_100;
	}

	public static int getPapel_MotorRotate_100() {
		return Papel_MotorRotate_100;
	}

	public static void setPapel_MotorRotate_100(int papel_MotorRotate_100) {
		Papel_MotorRotate_100 = papel_MotorRotate_100;
	}
	


	
	
	

}
