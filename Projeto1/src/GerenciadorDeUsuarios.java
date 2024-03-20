import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeUsuarios {
    private Map<String, Usuario> usuarios;

    public GerenciadorDeUsuarios() {
        usuarios = new HashMap<>();
    }

    public void cadastrarUsuario(String nome, String cpf, String telefone, String endereco, String cidade, String estado) {
        Usuario usuario = new Usuario(nome, cpf, telefone, endereco, cidade, estado);
        usuarios.put(cpf, usuario);
    }

    public Usuario pesquisarUsuario(String cpf) {
        return usuarios.get(cpf);
    }

    public void editarUsuario(String cpf, String nome, String telefone, String endereco, String cidade, String estado) {
        Usuario usuario = usuarios.get(cpf);
        if (usuario != null) {
            usuario.setNome(nome);
            usuario.setTelefone(telefone);
            usuario.setEndereco(endereco);
            usuario.setCidade(cidade);
            usuario.setEstado(estado);
        }
    }

    public void excluirUsuario(String cpf) {
        usuarios.remove(cpf);
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
