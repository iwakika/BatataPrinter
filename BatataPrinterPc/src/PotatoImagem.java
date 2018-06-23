import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

public class PotatoImagem {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		PotatoImagem img = new PotatoImagem("Xalakom");
		int[][] matriz = img.transformaMatrizPB();  
		
		System.out.println(imprimeMatrizEmString(matriz));


	}
		// TODO Auto-generated method stub
	
	
	private static BufferedImage imagem = null;
//	private i
	private String diretorio = "src\\img\\";
	private static int mod = -70;
	private static int valorMedio = 382;
	private String formato = ".png";
	
	public PotatoImagem(String nameFile) {
		this.diretorio += nameFile;
	}
	
	
	/**
	 * Carregar Buffer de Imagem
	 */
	public void carregarImagem() {
		File file = new File(diretorio + formato);
		System.out.println(file.getPath());
		try {
			
			imagem = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void MatrizToArrayBite() {
		
	}
	
	/**
	 * Carrega a imagem no buffer e transforma em uma Matriz PB, e gera uma copia com aimagem gerada
	 */
	public int[][] transformaMatrizPB(){
	
	carregarImagem();	 
	
	
	int h = imagem.getHeight();
	int w = imagem.getWidth();
	int[][] matriz = new int[h][w] ;
		
	int[] pixels = imagem.getRGB(0, 0, w, h, null, 0, w);
	//int[] pixelsCovertido = new i
	
	for (int col = 0; col < w; col++) {
		  for (int lin = 0; lin < h; lin++) {
			  //System.out.println(pixels[w * lin + col]);
			  Color c = new Color(pixels[w * lin + col], true);
			  int valorCor = c.getRed() + c.getGreen() + c.getBlue();
			 
			  if(valorCor < valorMedio + mod) {				
				  matriz[lin][col] = 1;
				  pixels[w * lin + col] = Color.BLACK.getRGB();
			  }else {
				  matriz[lin][col] = 0;
				  pixels[w * lin + col] = Color.WHITE.getRGB();
			  }
		      //System.out.println("lin:" + lin + ", Col:" +col);
		  }
		}
		imagem.setRGB(0, 0, w, h, pixels, 0, w);
		try {
			
			ImageIO.write(imagem, "PNG", new File(diretorio +"PB"+	formato ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matriz;
	}
	
	public static String imprimeMatrizEmString(int[][] m) {
		String matriz = "";
		// System.out.println("_________________");
		matriz = "_________________\n";
		try {
			int rows = m.length;
			int columns = m[0].length;
			String str = "|\t";
			

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if(m[i][j] == 0) {
					str +=  "" + "\t";
					}else{
						str +=  "*" + "\t";
					}
				}

				matriz += (str + "|\n");
				str = "|\t";
			}

		} catch (Exception e) {
			System.out.println("Matriz Vazia!");
		}
		return matriz;
	}
	



	
}
