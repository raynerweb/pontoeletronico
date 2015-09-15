package br.com.pontoeletronico.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.pontoeletronico.dominio.Usuario;

public class SessaoUtils {

	private static final String USUARIO_SESSAO = "usuarioSessao";

	public static void addUsuarioSessao(HttpServletRequest request, Usuario usuario) {
		HttpSession session = request.getSession();
		session.setAttribute(USUARIO_SESSAO, usuario);
	}

	public static void removeUsuarioSessao(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(USUARIO_SESSAO);
	}

	public static boolean isUsuarioNaSessao(HttpServletRequest request, Usuario usuario) {
		HttpSession session = request.getSession();
		Usuario usuarioLogado = usuario.getClass().cast(session.getAttribute(USUARIO_SESSAO));
		return usuarioLogado != null && usuario != null && usuarioLogado.getId().equals(usuario.getId());
	}

	public static Usuario getUsuarioLogado(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario usuarioLogado = Usuario.class.cast(session.getAttribute(USUARIO_SESSAO));
		return usuarioLogado;
	}

}
