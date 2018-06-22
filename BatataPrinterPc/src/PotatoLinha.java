
public class PotatoLinha {
	
	private int linhaTamanho;
	private EnumImprime acao;
	
	
	
	public PotatoLinha(int linha, EnumImprime acao) {
		super();
		this.linhaTamanho = linha;
		this.acao = acao;
	}
	public EnumImprime getAcao() {
		return acao;
	}
	public void setAcao(EnumImprime acao) {
		this.acao = acao;
	}
	public int getLinha() {
		return linhaTamanho;
	}
	public void setLinha(int linha) {
		this.linhaTamanho = linha;
	}
	
	
	

}
