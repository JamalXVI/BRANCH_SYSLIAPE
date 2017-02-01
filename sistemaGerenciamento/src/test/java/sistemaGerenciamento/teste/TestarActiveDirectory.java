package sistemaGerenciamento.teste;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.junit.Test;

import junit.framework.Assert;

public class TestarActiveDirectory {
	private static final int UF_NORMAL = 2;
	public void main() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, "ldap://172.25.0.5:389");

		// The value of Context.SECURITY_PRINCIPAL must be the logon username
		// with the domain name
		env.put(Context.SECURITY_PRINCIPAL, "administrator@rpo-acad.unaerpnet.br");

		// The value of the Context.SECURITY_CREDENTIALS should be the user's
		// password
		env.put(Context.SECURITY_CREDENTIALS, "(un1ver$1dadel10p3");

		DirContext ctx;

		try {
			// Authenticate the logon user
			ctx = new InitialDirContext(env);
			// Start checking if the user is within the organization unit(s)
			String searchBase = "OU=All Users, DC=rpo-acad,DC=unaerpnet,DC=br";
			String searchFilter = "anr=816068";
			SearchControls sCtrl = new SearchControls();
			sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration answer = ctx.search(searchBase, searchFilter, sCtrl);

			boolean pass = false;
			if (answer.hasMoreElements()) {
				SearchResult next = (SearchResult)answer.next();
				System.out.println(next);
				System.out.println(next.getAttributes());
				pass = true;
			}

			Assert.assertTrue(pass);
			/**
			 * Once the above line was executed successfully, the user is said
			 * to be authenticated and the InitialDirContext object will be
			 * created.
			 */

		} catch (NamingException ex) {
			// Authentication failed, just check on the exception and do
			// something about it.
			System.out.println(ex.getMessage());
		}

	}


	@Test
	public void adicionarUsuario() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, "ldap://172.25.0.5:636/");

		// The value of Context.SECURITY_PRINCIPAL must be the logon username
		// with the domain name
		env.put(Context.SECURITY_PRINCIPAL, "administrator@rpo-acad.unaerpnet.br");
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		// The value of the Context.SECURITY_CREDENTIALS should be the user's
		// password
		env.put(Context.SECURITY_CREDENTIALS, "(un1ver$1dadel10p3");
		
		String entryDN = "cn=123456,OU=2M,OU=Students,"
				+ "OU=All Users,DC=rpo-acad,DC=unaerpnet,DC=br";
		// entry's attributes

		Attribute cn = new BasicAttribute("cn", "123456");
		Attribute dn = new BasicAttribute("displayname","Luitheus Cazarino Bolsomito");
		Attribute sn = new BasicAttribute("sn", "Cazarino Bolsomito");
		Attribute gn = new BasicAttribute("givenname", "Luitheus");
		Attribute oc = new BasicAttribute("objectClass");
		Attribute nm = new BasicAttribute("userprincipalname","123456@rpo-acad.unaerpnet.br");
		Attribute sam = new BasicAttribute("samAccountName","123456");
		//Attribute conta = new BasicAttribute("msDS-UserAccountDisabled", "FALSE");
		oc.add("top");
		oc.add("person");
		oc.add("organizationalPerson");
		oc.add("user");
		LdapContext ctx = null;

		try {
			// get a handle to an Initial DirContext
			ctx = new InitialLdapContext(env, null);

			// build the entry
			BasicAttributes entry = new BasicAttributes();
			entry.put(cn);
			entry.put(sn);
			entry.put(nm);
			entry.put(oc);
			entry.put(dn);
			entry.put(gn);
			entry.put(sam);
		//	entry.put(senha);
			entry.put("pwdLastSet", "-1");
		//	entry.put("userAccountControl", "66048"); //enable account
			//entry.put("userAccountControl", ~UF_NORMAL);
			// Add the entry

			ctx.createSubcontext(entryDN, entry);
			adicionarGrupo(ctx);
			adicionarPermissao(ctx);
			adicionarSenha(ctx);
			 System.out.println( "AddUser: added entry " + entryDN + ".");

		} catch (NamingException e) {
			System.err.println("AddUser: error adding entry." + e);
		}
	}
	public void adicionarGrupo(LdapContext contexto)
	{
		ModificationItem[] mods = new ModificationItem[1];
		String userDn="cn=123456,OU=2M,OU=Students,"
				+ "OU=All Users,DC=rpo-acad,DC=unaerpnet,DC=br";
		String groupDn="cn=CFS_RPO_ACAD-Alunos,OU=All Groups,DC=rpo-acad,DC=unaerpnet,DC=br";
		String studentsDn="cn=Students,OU=All Groups,DC=rpo-acad,DC=unaerpnet,DC=br";
		Attribute mod =new BasicAttribute("member",userDn);
		mods[0] =new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
		try {
			contexto.modifyAttributes(groupDn, mods);
			contexto.modifyAttributes(studentsDn, mods);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void adicionarPermissao(LdapContext contexto)
	{
		ModificationItem[] mods = new ModificationItem[1];
		String userDn="cn=123456,OU=2M,OU=Students,"
				+ "OU=All Users,DC=rpo-acad,DC=unaerpnet,DC=br";
		Attribute mod =new BasicAttribute("userAccountControl", "544");
		mods[0] =new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod);
		try {
			contexto.modifyAttributes(userDn, mods);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void adicionarSenha(LdapContext contexto)
	{
		String quotedPassword = "\"" + "123456" + "\""; 
		char unicodePwd[] = quotedPassword.toCharArray(); 
		byte pwdArray[] = new byte[unicodePwd.length * 2]; 
		for (int i=0; i<unicodePwd.length; i++) { 
		pwdArray[i*2 + 1] = (byte) (unicodePwd[i] >>> 8); 
		pwdArray[i*2 + 0] = (byte) (unicodePwd[i] & 0xff); 
		}
		ModificationItem[] mods = new ModificationItem[1];
		String userDn="cn=123456,OU=2M,OU=Students,"
				+ "OU=All Users,DC=rpo-acad,DC=unaerpnet,DC=br";
		Attribute mod =new BasicAttribute("UnicodePwd", pwdArray);
		mods[0] =new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod);
		try {
			contexto.modifyAttributes(userDn, mods);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
