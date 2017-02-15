package br.com.sistemaGerenciamento.conexao;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.liape.sistemaGerenciamento.adServerModel.Cadastro;
import br.com.liape.sistemaGerenciamento.adServerModel.OrganizationalUnit;
import br.com.liape.sistemaGerenciamento.adServerModel.Pessoa;

public class WebService {
	public static String Deletar(String nome) {
        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();

        String resposta = null;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getDeletarUsuario());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo usuario = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        usuario.setName("usuario");
        // Set CODIGO
        usuario.setValue(nome);
        // Set dataType
        usuario.setType(String.class);
        //ADICIONA
        request.addProperty(usuario);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {

            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getDeletarUsuario(), envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            resposta = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Verifique sua Conexão";

        } finally {

            androidHttpTransport.reset();
        }

        return resposta;
    }

    public static ArrayList<Pessoa> Listar(String Codigo, String tipoUsuario) {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();
        ArrayList<Pessoa> Lista = null;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getListarUsuario());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo CodigoPesquisa = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        CodigoPesquisa.setName("filtro");
        // Set CODIGO
        CodigoPesquisa.setValue(Codigo);
        // Set dataType
        CodigoPesquisa.setType(String.class);

        PropertyInfo Filtro = new PropertyInfo();
        // Set PARAMETRO
        Filtro.setName("tipoUsuario");
        // Set CODIGO				
        Filtro.setValue(tipoUsuario);
        // Set dataType
        Filtro.setType(String.class);

        //ADICIONA
        request.addProperty(CodigoPesquisa);
        request.addProperty(Filtro);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {
            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getListarUsuario(), envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.getResponse();
			// Assign static variable

            if (response != null) {
                Lista = new ArrayList<Pessoa>();

                for (int i = 0; i < response.getPropertyCount(); i++) {

                    SoapObject objSoap = (SoapObject) response.getProperty(i);
                    Pessoa pessoa = new Pessoa(objSoap.getProperty(0).toString(), objSoap.getProperty(1).toString());
                    Lista.add(pessoa);

                }
            } else {

                Lista = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Lista;
        } finally {
            androidHttpTransport.reset();

        }

        return Lista;
    }

    public static ArrayList<OrganizationalUnit> ListarCursos() {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();
        ArrayList<OrganizationalUnit> Lista = new ArrayList<OrganizationalUnit>();

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getListarCursos());

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {
            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getListarCursos(), envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.getResponse();
			// Assign static variable

            if (response != null) {

                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject objSoap = (SoapObject) response.getProperty(i);
                    OrganizationalUnit curso = new OrganizationalUnit(objSoap.getProperty(0).toString(), objSoap.getProperty(1).toString(), objSoap.getProperty(2).toString());
                    Lista.add(curso);

                }
            } else {

                Lista = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Lista;
        } finally {
            androidHttpTransport.reset();

        }

        return Lista;
    }

    public static String Resetar(String Codigo, String Senha) {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();

        String resposta = null;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getResetarSenha());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo username = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        username.setName("username");
        // Set CODIGO
        username.setValue(Codigo);
        // Set dataType
        username.setType(String.class);
        //ADICIONA
        request.addProperty(username);

        PropertyInfo password = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        password.setName("password");
        // Set CODIGO
        password.setValue(Senha);
        // Set dataType
        password.setType(String.class);
        //ADICIONA
        request.addProperty(password);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {

            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getResetarSenha(), envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            resposta = response.toString();

			// Assign static variable
        } catch (Exception e) {
            e.printStackTrace();
            return "Verifique sua Conex�o";

        } finally {

            androidHttpTransport.reset();
        }

        return resposta;

    }

   

    

    public static String login(String Usuario, String Senha) {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();

        String resposta;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getLogin());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo usuario = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        usuario.setName("usuario");
        // Set CODIGO
        usuario.setValue(Usuario);
        // Set dataType
        usuario.setType(String.class);
        //ADICIONA
        request.addProperty(usuario);

        PropertyInfo senha = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        senha.setName("senha");
        // Set CODIGO
        senha.setValue(Senha);
        // Set dataType
        senha.setType(String.class);
        //ADICIONA
        request.addProperty(senha);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {

            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getLogin(), envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            resposta = response.toString();

			// Assign static variable
        } catch (Exception e) {
            e.printStackTrace();
            resposta = "-1";

        } finally {
            androidHttpTransport.reset();

        }

        return resposta;

    }

    public static String AdicionarCFS(String Codigo, String tipoUsuario) {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();

        String resposta = null;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getAdicionarCfs());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo username = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        username.setName("username");
        // Set CODIGO
        username.setValue(Codigo);
        // Set dataType
        username.setType(String.class);
        //ADICIONA
        request.addProperty(username);

        PropertyInfo tipo = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        tipo.setName("tipoUsuario");
        // Set CODIGO
        tipo.setValue(tipoUsuario);
        // Set dataType
        tipo.setType(String.class);
        //ADICIONA
        request.addProperty(tipo);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {

            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getAdicionarCfs(), envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            resposta = response.toString();

			// Assign static variable
        } catch (Exception e) {
            e.printStackTrace();
            return "Verifique sua Conex�o";

        } finally {
            androidHttpTransport.reset();

        }

        return resposta;

    }

    public static String Cadastrar(Cadastro cad) {

        ConexaoAdServer ConexaoAdServer = new ConexaoAdServer();

        String resposta = null;

        // Create request
        SoapObject request = new SoapObject(ConexaoAdServer.getNAMESPACE(), ConexaoAdServer.getCadastrar());

        // ADICIONA O PARAMETRO REQUERIDO PARA O WebService
        PropertyInfo username = new PropertyInfo();

		//ATRIBUIR VALORES
        // Set PARAMETRO
        username.setName("usuario");
        // Set CODIGO
        username.setValue(cad.getCodigo());
        // Set dataType
        username.setType(String.class);
        //ADICIONA
        request.addProperty(username);

        PropertyInfo nome = new PropertyInfo();

        nome.setName("nome");
        nome.setValue(cad.getNome());
        nome.setType(String.class);
        request.addProperty(nome);

        PropertyInfo sobreNome = new PropertyInfo();

        sobreNome.setName("sobrenome");
        sobreNome.setValue(cad.getSobreNome());
        sobreNome.setType(String.class);
        request.addProperty(sobreNome);

        PropertyInfo senha = new PropertyInfo();

        senha.setName("senha");
        senha.setValue(cad.getSenha());
        senha.setType(String.class);
        request.addProperty(senha);

        PropertyInfo tipo = new PropertyInfo();

        tipo.setName("tipoUsuario");
        tipo.setValue(cad.getTipoUsuario());
        tipo.setType(String.class);
        request.addProperty(tipo);

        PropertyInfo curso = new PropertyInfo();

        curso.setName("curso");
        curso.setValue(cad.getCurso());
        curso.setType(String.class);
        request.addProperty(curso);

        // CRIAR envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
		// Create HTTP call object

        HttpTransportSE androidHttpTransport = new HttpTransportSE(ConexaoAdServer.getURL());

        try {

            // CHAMADA O WebService
            androidHttpTransport.call(ConexaoAdServer.getSOAP_ACTION() + ConexaoAdServer.getCadastrar(), envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            resposta = response.toString();

			// Assign static variable
        } catch (Exception e) {
            e.printStackTrace();
            return "Verifique sua Conex�o";

        } finally {

            androidHttpTransport.reset();
        }

        return resposta;

    }
}
