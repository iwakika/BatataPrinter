package Maneger;

import Maneger.PotatoPrinter.EnumImprime;

public class PotatoLinha {
	
	private int linhaQtd;
	private EnumImprime acao;
	
	
	
	public PotatoLinha(int linha, EnumImprime acao) {
		super();
		this.linhaQtd = linha;
		this.acao = acao;
	}
	public EnumImprime getAcao() {
		return acao;
	}
	public void setAcao(EnumImprime acao) {
		this.acao = acao;
	}
	public int getLinhaQtd() {
		return linhaQtd;
	}
	public void setLinhaQtd(int linha) {
		this.linhaQtd = linha;
	}
	
	
	

}
