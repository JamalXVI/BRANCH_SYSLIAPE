package br.com.liape.sistemaGerenciamento.seguranca;


import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.liape.sistemaGerenciamento.controllers.ErrosController;
import br.com.liape.sistemaGerenciamento.model.Permissao;


@Intercepts(after=AutorizacaoInterceptor.class)
public class AutorizacaoNivelInterceptor {
    private ControllerMethod controllerMethod;
    private UsuarioLogado usuarioLogado;
    private Result result;
    public AutorizacaoNivelInterceptor(){}
    @Inject
    public AutorizacaoNivelInterceptor(ControllerMethod controllerMethod, UsuarioLogado usuarioLogado, Result result){
        this.controllerMethod = controllerMethod;
        this.usuarioLogado = usuarioLogado;
        this.result = result;
    }
    @Accepts
    public boolean accepts(){
        return controllerMethod.containsAnnotation(NivelPermissao.class);
    }
    @AroundCall
    public void intercept(SimpleInterceptorStack stack){
    	int nivel = 1;
    	NivelPermissao[] annotations = controllerMethod.getMethod().getAnnotationsByType(NivelPermissao.class);
    	for (NivelPermissao nivelPermissao : annotations) {
			nivel = nivelPermissao.idPermissao();
		}
        if(usuarioLogado.isLogado()){
        	boolean passou = false;
        	for (Permissao permissao : usuarioLogado.pegarAutorizacao()) {
        		if (nivel ==  permissao.getId() || permissao.getId() == 1) {
                    passou = true;
        		}
			}
        	
        	if (passou) {
        		stack.next();
			}else{
				result.redirectTo(ErrosController.class).erro_acesso();
			}
        }else{
            result.redirectTo(ErrosController.class).erro_acesso();
        }
    }
}
