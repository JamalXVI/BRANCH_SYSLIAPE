package br.com.liape.sistemaGerenciamento.outros;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.liape.sistemaGerenciamento.model.FotoPerfil;
/*
 * CLASSE RESPONSÁVEL POR TRATAMENTO DE IMAGEM
 */
public class TransformaImagem {
	/*
	 * RENDERIZAR IMAGEM: COLETAR O ARQUIVO ENVIADO PELO CLIENTE E TRANSFORMA-LO EM BUFFERED IMAGE
	 */
	public BufferedImage renderizarImagem(UploadedFile imagem)
	{
		if (imagem != null) {
			BufferedImage image;
			try {
				image = ImageIO.read(imagem.getFile());
				//CORTAR IMAGEM
				return cortarImagem(image);
			} catch (IOException e) {
				System.out.println("RenderizarImagem - ERRO: "+ e.getMessage());
			}
		}
		return null;
	}
	
	/*
	 * TOINPUTSTREAM: COLETAR O BUFFERED IMAGE E TRANSFORMA-LO EM INPUTSTREAM(BYTE[])
	 */
	public static InputStream toInputStream(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
	/*
	 * RETORNAR IMAGEM EM FORMA DE BASE64 (STRING) PARA REQUISIÇÕES TIPO AJAX
	 */
	public String retornarImagem(List<FotoPerfil> fotos, int idPessoa)
	{
		BufferedImage foto;
		
		if (fotos.size() != 0) {
			foto = fotos.get(0).getFoto();
		}else{
			try {
				foto = ImageIO.read(new URL(getClass().getClassLoader().
				        getResource("/img/"+"Desconhecido.png"), "Desconhecido.png"));
			} catch (MalformedURLException e) {
				e.getMessage();
				return null;
			} catch (IOException e) {
				e.getMessage();
				return null;
			}
		}
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try {
			ImageIO.write( foto, "jpg", baos );
			baos.flush();
	    	byte[] imagem = baos.toByteArray();
	    	baos.close();
	    	return Base64.getEncoder().encodeToString(imagem);
	    	//return new ByteArrayDownload(imagem, "image/png", String.valueOf(idPessoa)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	/*
	 * CORTE DA IMAGEM QUANDO VINDA DO CLIENTE
	 */
	private BufferedImage cortarImagem(BufferedImage src) {
		  int width = src.getWidth();
		  int height = src.getHeight();
		  int distancia = Math.abs(((height-width)/2));
		  int x = (width > height ? distancia : 0 );
		  int y = (height > width ? distancia : 0 );
		  int h = (height > width ? width : height);
		  int w = (width > height ? height : width);
//		  System.out.println("X: "+x+" Y: "+y+" W: "+w+" H: "+h+"D "+distancia+" width: "+width+" height: "+height);
	      BufferedImage dest = src.getSubimage(x,y,w,h );
	      if (dest.getWidth() > 360) {
	    	  dest = retornarImagemEscalada(dest);
	      }
	      return dest; 
	  }
	private BufferedImage retornarImagemEscalada(BufferedImage original)
	{
		BufferedImage newImage = new BufferedImage(360, 360, BufferedImage.TYPE_INT_RGB);

		Graphics2D g = newImage.createGraphics();
		g.drawImage(original, 0, 0, 360, 360, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		return newImage;
		/*
		BufferedImage before = encoded;
		int w = before.getWidth();
		int h = before.getHeight();
		System.out.println(w);
		float escala  = 180.00f/w;
		System.out.println(escala);
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(escala, escala);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		after = scaleOp.filter(before, after);
		return after;
		*/
	}
}
