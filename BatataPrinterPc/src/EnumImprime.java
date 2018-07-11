


public enum EnumImprime{		
	
	NAO_DESENHA(0),
	DESENHA(1),
	PROXIMA_LINHA(2),
	FINALIZA(3),
	INGNORA(4);
	
	int value;
	EnumImprime(int i) {
		this.value = i;
	
	}
}