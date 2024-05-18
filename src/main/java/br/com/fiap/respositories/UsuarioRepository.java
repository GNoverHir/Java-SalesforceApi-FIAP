package br.com.fiap.respositories;

import br.com.fiap.models.Usuario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import static java.util.Map.entry;



public class UsuarioRepository {

    public static final Logger LOGGER = LogManager.getLogger(UsuarioRepository.class);
    public static String URL_ORACLE;
    public static String USER;
    public static String PASSWORD;

    public static final String TABLE_NAME = "T_SF_USUARIOS";

    public static final Map<String, String> TABLE_COLUMNS = Map.ofEntries(
            entry("NOME", "nm_usuario"),
            entry("EMAIL", "email_usuario"),
            entry("TELEFONE", "nr_telefone"),
            entry("TAMEMPRESA", "nr_tamanho_empresa"),
            entry("PAIS", "nm_pais"),
            entry("IDIOMA", "nm_idioma"),
            entry("SENHA", "senha")
    );

    public UsuarioRepository(){
        try(var inputStream = getClass().getClassLoader()
                .getResourceAsStream("database.properties"))
        {
            var properties = new Properties();
            properties.load(inputStream);
            URL_ORACLE = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Usuario> verificacaoLogin(String email, String senha) {
        try (var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
             var preparedStatement = connection.prepareStatement(
                     "SELECT * FROM " + TABLE_NAME + " WHERE email_usuario = ? AND senha = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Usuario(
                            resultSet.getString(TABLE_COLUMNS.get("IDIOMA")),
                            resultSet.getString(TABLE_COLUMNS.get("PAIS")),
                            resultSet.getInt(TABLE_COLUMNS.get("TAMEMPRESA")),
                            resultSet.getString(TABLE_COLUMNS.get("TELEFONE")),
                            resultSet.getString(TABLE_COLUMNS.get("SENHA")),
                            resultSet.getString(TABLE_COLUMNS.get("EMAIL")),
                            resultSet.getString(TABLE_COLUMNS.get("NOME"))
                    ));
                }
            }
        } catch (SQLException e) {
                    LOGGER.error("Erro ao tentar verificar o login do usuario!",
                    e.getMessage());
        }
        LOGGER.info("Usuario verificado com sucesso!");
        return Optional.empty();
    }

    public void cadastroUsuario(Usuario usuario){
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?)"
                            .formatted(TABLE_NAME,
                                    TABLE_COLUMNS.get("NOME"),
                                    TABLE_COLUMNS.get("EMAIL"),
                                    TABLE_COLUMNS.get("TELEFONE"),
                                    TABLE_COLUMNS.get("TAMEMPRESA"),
                                    TABLE_COLUMNS.get("PAIS"),
                                    TABLE_COLUMNS.get("IDIOMA"),
                                    TABLE_COLUMNS.get("SENHA")
                            ))
        ) {
            preparedStatement.setString(1, usuario.getNomeCompleto());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getTelefone());
            preparedStatement.setInt(4, usuario.getTamanhoEmpresa());
            preparedStatement.setString(5, usuario.getPais());
            preparedStatement.setString(6, usuario.getIdioma());
            preparedStatement.setString(7, usuario.getSenha());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
                    LOGGER.error("Erro ao cadastrar usuario!",
                    e.getMessage());
        }
        LOGGER.info("Usuario cadastrado com sucesso!");
        return;

    }

}
